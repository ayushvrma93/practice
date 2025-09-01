package com.twentyfour.atlassian.machinecoding;


import lombok.Data;

import java.util.*;

//
//file1.txt (size: 200) in collection "collection1"
//file2.txt (size: 200) in collection "collection1"
//file3.txt (size: 300) in collection "collection2"
//file4.txt (size: 700) in collection "collection3"
//
//total file - 700MB
//collection1 - 400MB
//
//1 node:
//    name: collection1
//    filesize: 200MB
//
//    name: collection2
//    filesize: 400MB
//
//

public class Q1 {

    int totalSize;
    @Data
    static class File{
        String fileName;

        int fileSize;

        File(String fileName, int fileSize){
            this.fileName = fileName;
            this.fileSize = fileSize;
        }
    }

    static class Resources{
        String collectionName;
        int collectionSize;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Resources resources = (Resources) o;
            return Objects.equals(collectionName, resources.collectionName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(collectionName);
        }

        public Resources(String collectionName1, int size) {
            this.collectionSize = size;
            this.collectionName = collectionName1;
        }
    }

    @Data
    static class ResourceDetails{
        String collectionName;
        File file;

        public ResourceDetails(String collection1, File file) {
            this.collectionName = collection1;
            this.file = file;
        }

        @Override
        public int hashCode() {
            return this.collectionName.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return this.collectionName.equals(obj);
        }
    }

    Map<String, List<File>> collectionToFileMap = new HashMap<>();
    Comparator<Resources> comparator = (o1, o2) -> Integer.compare(o2.collectionSize, o1.collectionSize);
    List<Resources> resourcesList = new ArrayList<>();

    public void add(ResourceDetails resourceDetails){
        String collectionName = resourceDetails.collectionName;

        List<File> files;

        if(collectionToFileMap.containsKey(collectionName)){
            files = collectionToFileMap.get(collectionName);
        } else {
            files = new ArrayList<>();
        }

        File newFile = new File(resourceDetails.file.getFileName(), resourceDetails.file.getFileSize());
        files.add(newFile);
        collectionToFileMap.put(collectionName, files);

//        collectionToFileMap.computeIfAbsent(collectionName, k -> new ArrayList<>()).
//                add(new File(resourceDetails.file.getFileName(), resourceDetails.file.getFileSize()));

        totalSize += resourceDetails.file.getFileSize();
        int size=0;

        for(File f : files){
            size+=f.fileSize;
        }

        Resources newResource = new Resources(collectionName, size);
        boolean found = false;

        for(Resources curr : resourcesList){
            if(curr.collectionName.equals(newResource.collectionName)){
                curr.collectionSize = newResource.collectionSize;
                found = true;
                break;
            }
        }
        if(!found){
            resourcesList.add(newResource);
        }
        resourcesList.sort(comparator);
    }

    public List<Resources> getNMax(int n){
        List<Resources> resourcesList1 = new ArrayList<>();

        int end = Math.min(n, resourcesList.size());

        for(int i=0; i<end; i++){
            resourcesList1.add(resourcesList.get(i));
        }

        return resourcesList1;
    }

    public int getTotalSize(){
        return this.totalSize;
    }

    /*
    Time complexity = O(no. of files) + log(k) => k= no. of collections;
    Space complexity = O(no. of files) + O(no. of collections)
     */

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        ResourceDetails resourceDetails1 = new ResourceDetails("collection1", new File("file1.txt", 200));
        ResourceDetails resourceDetails2 = new ResourceDetails("collection1", new File("file2.txt", 200));
        ResourceDetails resourceDetails3 = new ResourceDetails("collection2", new File("file3.txt", 400));
        ResourceDetails resourceDetails4 = new ResourceDetails("collection3", new File("file4.txt", 800));

        q1.add(resourceDetails1);
        q1.add(resourceDetails2);
        q1.add(resourceDetails3);
        q1.add(resourceDetails4);

        System.out.println(q1.getTotalSize());
        List<Resources> resourcesList = q1.getNMax(2);
        for(Resources resources : resourcesList){
            System.out.println(resources.collectionName + " " + resources.collectionSize);
        }

    }
}
