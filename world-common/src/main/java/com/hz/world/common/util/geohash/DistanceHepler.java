package com.hz.world.common.util.geohash;

public class DistanceHepler {
    private final static double Earth_Radius = 6378.137f;
 
    public static double distance(double lat1, double lng1, double lat2, double lng2) {
   /*     double x1 = Math.cos(lat1) * Math.cos(lng1);
        double y1 = Math.cos(lat1) * Math.sin(lng1);
        double z1 = Math.sin(lat1);

        double x2 = Math.cos(lat2) * Math.cos(lng2);
        double y2 = Math.cos(lat2) * Math.sin(lng2);
        double z2 = Math.sin(lat2);

        double lineDistance =
                Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        double realDistance = Earth_Radius * Math.PI * 2 * Math.asin(0.5 * lineDistance) / 180;
        return realDistance;
        */
        double radLat1 = getRadian(lat1);
		double radLat2 = getRadian(lat2);
		double a = radLat1 - radLat2;// 两点纬度差
		double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
				* Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * Earth_Radius;
		return s ;
        
    }
    private static double getRadian(double degree) {
		return degree * Math.PI / 180.0;
	}

    public static void main(String[] args) {
        System.out.println(DistanceHepler.distance(39.992907, 116.391728, 39.985336, 116.37736));
    }
}
