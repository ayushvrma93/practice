package com.twentytwo.flipkart.machinecoding.entity;

import com.twentytwo.flipkart.machinecoding.entity.enums.BookingStatus;
import com.twentytwo.flipkart.machinecoding.entity.enums.WorkOutType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Booking {

    private int centerId;
    private String centerName;
    private int userId;
    private int slotId;
    private WorkOutType workOutType;
    private BookingStatus status;


    public Booking(int userId, int slotId, WorkOutType workOutType, BookingStatus status) {
        this.userId = userId;
        this.slotId = slotId;
        this.workOutType = workOutType;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return centerId == booking.centerId && userId == booking.userId && slotId == booking.slotId && Objects.equals(centerName, booking.centerName) && workOutType == booking.workOutType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centerId, centerName, userId, slotId, workOutType);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "centerId=" + centerId +
                ", centerName='" + centerName + '\'' +
                ", userId=" + userId +
                ", slotId=" + slotId +
                ", workOutType=" + workOutType +
                ", status=" + status +
                '}';
    }
}
