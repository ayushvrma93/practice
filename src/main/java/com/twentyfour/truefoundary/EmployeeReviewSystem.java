package com.twentyfour.truefoundary;


import lombok.Data;

import java.time.LocalDate;
import java.util.*;


@Data
class ReviewAndAverage{
    List<Review> reviews = new ArrayList<>();
    Double average = 0.0;
}
class Review{
    String employeeId;
    String reviewerId;

    LocalDate reviewDate;

    Double performanceRating;

    String feedbackComment;

    public Review(String employeeId, String reviewerId, LocalDate reviewDate, Double performanceRating, String feedbackComment) {
        this.employeeId = employeeId;
        this.reviewerId = reviewerId;
        this.reviewDate = reviewDate;
        this.performanceRating = performanceRating;
        this.feedbackComment = feedbackComment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "employeeId='" + employeeId + '\'' +
                ", reviewerId='" + reviewerId + '\'' +
                ", reviewDate=" + reviewDate +
                ", performanceRating=" + performanceRating +
                ", feedbackComment='" + feedbackComment + '\'' +
                '}';
    }
}

class EmployeeRatings{
    String employeeId;
    Double rating;

    public EmployeeRatings(String employeeId, Double rating) {
        this.employeeId = employeeId;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "EmployeeRatings{" +
                "employeeId='" + employeeId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
public class EmployeeReviewSystem {

    Map<String, ReviewAndAverage> employeeAndReview = new HashMap<>();
    Map<String, ReviewAndAverage> reviewerAndReviews = new HashMap<>();

    NavigableMap<LocalDate, List<Review>> dateAndReviewsMap = new TreeMap<>();

    Comparator<EmployeeRatings> comp = Comparator.comparing(o -> -1 * o.rating);

    List<EmployeeRatings> list = new ArrayList<>();

    public List<Review> getReviewsForAnEmployee(String employeeId){
        if(!employeeAndReview.containsKey(employeeId)){
            return null;
        }
        return employeeAndReview.get(employeeId).getReviews();
    }

    public Double getRatingForAnEmployee(String employeeId){
        if(!employeeAndReview.containsKey(employeeId)){
            return null;
        }
        return employeeAndReview.get(employeeId).getAverage();
    }

    public List<Review> getReviewsByReviewer(String reviewerId){
        if(!reviewerAndReviews.containsKey(reviewerId)){
            return null;
        }
        return reviewerAndReviews.get(reviewerId).getReviews();
    }

    public List<Review> getReviewsByDateRange(LocalDate start, LocalDate end){
        NavigableMap dateRangeMap =  dateAndReviewsMap.subMap(start, true, end, true);
        List<Review> reviews = new ArrayList<>();

        for(Map.Entry<LocalDate, List<Review>> entry : dateAndReviewsMap.entrySet()){
            reviews.addAll(entry.getValue());
        }
        return reviews;
    }

    public void addReview(Review review){
        employeeAndReview.computeIfAbsent(review.employeeId, k -> new ReviewAndAverage()).getReviews().add(review);
        ReviewAndAverage ra = employeeAndReview.get(review.employeeId);

        int size = ra.getReviews().size();
        ra.average = ((ra.average * (size-1)) + review.performanceRating)/size ;

        reviewerAndReviews.computeIfAbsent(review.reviewerId, k -> new ReviewAndAverage()).getReviews().add(review);
        dateAndReviewsMap.computeIfAbsent(review.reviewDate, k -> new ArrayList<>()).add(review);

        arrange(review.employeeId, ra.average);
    }

    public void arrange(String employeeId, Double rating){

        for(EmployeeRatings er : list){
            if(er.employeeId.equals(employeeId)){
                er.rating = rating;
                return;
            }
        }

        list.add(new EmployeeRatings(employeeId, rating));
        Collections.sort(list, comp);
    }

    public List<EmployeeRatings> getTopEmployees(int n){
        List<EmployeeRatings> ratings = new ArrayList<>();

        for(EmployeeRatings employeeRatings : list){
            if(n-- == 0) break;
            ratings.add(employeeRatings);
        }
        return ratings;
    }

    public static void main(String[] args) {

        EmployeeReviewSystem employeeReviewSystem = new EmployeeReviewSystem();

        Review review1 = new Review("1", "101", LocalDate.of(2024, 04, 20), 4.5, "Excellent work");
        Review review2 = new Review("1", "102", LocalDate.of(2024, 04, 22), 3.8, "Good effort");
        Review review3 = new Review("2", "101", LocalDate.of(2024, 04, 23), 4.2, "Consistently meets expectations");
        Review review4 = new Review("3", "103", LocalDate.of(2024, 04, 25), 4.8, "Outstanding performance");

        employeeReviewSystem.addReview(review1);
        employeeReviewSystem.addReview(review2);
        employeeReviewSystem.addReview(review3);
        employeeReviewSystem.addReview(review4);

        System.out.println(employeeReviewSystem.getReviewsForAnEmployee("1"));
        System.out.println(employeeReviewSystem.getRatingForAnEmployee("1"));
        System.out.println(employeeReviewSystem.getReviewsByReviewer("101"));

        System.out.println(employeeReviewSystem.getReviewsByDateRange(LocalDate.of(2024, 04, 20), LocalDate.of(2024, 04, 23)));
        System.out.println(employeeReviewSystem.getTopEmployees(3));
    }
}
