package com.twentyfour;

public class IPValidation {
    public String validIPAddress(String queryIP) {
        String[] splitted;
        boolean isValid;

        splitted = queryIP.split("\\.");

        if(splitted.length == 4){
            isValid = checkIPv4(splitted);
            if(isValid) return "IPv4";
        } else {
            splitted = queryIP.split(":");
            if(splitted.length == 8){
                isValid = checkIPv6(splitted);
                if(isValid) return "IPv6";
            }
        }
        return "Neither";
    }

    public boolean checkIPv4(String[] splitted){

        for(int i=0; i<splitted.length; i++){
            try{
                String numStr = splitted[i];
                if(numStr.startsWith("0") && numStr.length() > 1){
                    return false;
                }
                Integer num = Integer.valueOf(numStr);
                if(num < 0 || num > 255){
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }

    public boolean checkIPv6(String[] splitted){
        for(int i=0; i<splitted.length; i++){
            String str = splitted[i];
            if(str.length() > 4) return false;

            for(int j=0; j<str.length(); j++){
                int ascii = (int) (str.charAt(j));
                if((ascii < (int)('0') || ascii > (int)('9')) && (ascii < (int)('a') || ascii > (int)('f'))
                        && (ascii < (int)('A') || ascii > (int)('F'))){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IPValidation ipValidation = new IPValidation();
        System.out.println(ipValidation.validIPAddress("172.16.254.1"));
//        System.out.println(ipValidation.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    }
}
