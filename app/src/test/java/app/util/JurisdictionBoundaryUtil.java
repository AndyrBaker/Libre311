package app.util;

import org.locationtech.jts.geom.Coordinate;

public class JurisdictionBoundaryUtil {


    // a large boundary roughly covering St. Louis metro area (format [lat, lng])
    public static final Double[][] DEFAULT_BOUNDS = new Double[][]{
        new Double[]{38.88908245157475, -90.82207996696539},
        new Double[]{38.28511105115126, -90.32668241294714},
        new Double[]{38.73098601356233, -89.86006757704696},
        new Double[]{39.04413540068816, -90.36058752072049},
        new Double[]{38.88908245157475, -90.82207996696539},
    };

    public static final Coordinate IN_BOUNDS_COORDINATE = new Coordinate(-90.29517238194957,
        38.689033913397765);

}
