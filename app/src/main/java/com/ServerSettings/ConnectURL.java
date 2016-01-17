package com.ServerSettings;




public class ConnectURL {

public static String LOGIN="http://"+Constants.CONNECT_IP+":"+Constants.CONNECT_PORT+"/"+Constants.CONNECT_CONTEXT
        +"/LoginAuth?";
public static String USER_REGISTER="http://"+Constants.CONNECT_IP+":"+Constants.CONNECT_PORT+"/"+Constants.CONNECT_CONTEXT
        +"/UserRegister?";
        public static String GETVIDEO="http://"+Constants.CONNECT_IP+":"+Constants.CONNECT_PORT+"/"+Constants.CONNECT_CONTEXT
                +"/GetVideos";
        public static String PLAYVIDEO="http://"+Constants.CONNECT_IP+":"+Constants.CONNECT_PORT+"/"+Constants.CONNECT_CONTEXT
                +"/Videos/";
}
