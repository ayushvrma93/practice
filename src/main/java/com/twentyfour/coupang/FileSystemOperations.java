package com.twentyfour.coupang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemOperations {

    class FileSystemObject{
        String name;
        String type;
    }
    class File extends FileSystemObject{
        Double size;
    }

    class Directory extends FileSystemObject{
        Map<String, FileSystemObject> nameAndObjects = new HashMap<>();
    }

    public List<String> ls(String path){
        int j = 0;
        String[] splitted = path.split("/");

        return null;
    }

//    private void lsUtil(String path, int i, String[] splitted, FileSystemObject fileSystemObject, List<String> names){
//        if(fileSystemObject.type.equals("File") && i == splitted.length-1 && fileSystemObject.name.equals(splitted.length-1)) {
//            FileSystemObject fObject = fileSystemObject.get(i);
//            names.add(fObject.name);
//        }
//
//        Map<String, FileSystemObject> fileSystemObjectMap = fileSystemObject;
//        if(i == splitted.length-1 && fileSystemObject.containsKey(i)){
//            FileSystemObject fObject = fileSystemObject.get(i);
//
//            if(fObject.type.equals("File"))
//                names.add(fObject.name);
//            } else {
//                Directory dir = (Directory) fObject;
//                for(Map.Entry<String, FileSystemObject> f : dir.nameAndObjects.entrySet()){
//                    names.add(f.getKey());
//                }
//            }
//            return;
//        }
//
//        if(!fileSystemObject.containsKey(i)){
//            return;
//        }
//
//        for(i=0; i<path.length(); i++){
//            if(fileSystemObject.containsKey(splitted[i])){
//                FileSystemObject fileSystemObjectMap = fileSystemObject.get(splitted[i]);
//                lsUtil(path, i+1, splitted, fileSystemObjectMap., names);
//            }
//        }
//    }
}

