package com.example.sittings_views;

public class SeatsType {
    public static final String BUS_SEATS = "bus_seats";
    public static final String THEATER_SEATS = "theater_seats";
    public static final String PLANE_SEATS = "plane_seats";
    public static final String AUDITORIUM_SEATS = "auditorium_seats";

    public static String getSeatLayout(String type) {
        switch (type) {
            case BUS_SEATS:
                return "_________R/"
                        + "UU_UUU/"
                        + "______/"
                        + "UU__UU/"
                        + "UU__UU/"
                        + "AA__UU/"
                        + "AA__UU/"
                        + "UU__UU/"
                        + "AA__UU/"
                        + "AA__AA/"
                        + "AA__UU/"
                        + "AA__UU/"
                        + "AA__UU/"
                        + "AA__UU/"
                        + "AA__UU/"
                        + "UUUUUU/";

            case THEATER_SEATS:
                return "_AAAAAAAA_RRRRRR_/"
                        + "_________________/"
                        + "UU__AAAARRRRR__RR/"
                        + "AA__UUUAAAAAA__RR/"
                        + "UU__AAAAAAAAA__RR/"
                        + "UU__AARUUUURR__AA/"
                        + "UU__UUUA_RRRR__AA/"
                        + "AA__AAAA_RRAA__UU/"
                        + "UU__AARR_UUUU__RR/"
                        + "AA__UUAA_UURR__RR/"
                        + "_________________/"
                        + "RR_AAAAAAAUUUU_RR/"
                        + "RR_AAAAAAAAAAA_AA/"
                        + "AA_UUAAAAAUUUU_AA/"
                        + "AA_AAAAAAUUUUU_AA/"
                        + "_________________/";

            case PLANE_SEATS:
                return "_AAA_AAA_/"
                        + "_________________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "AA_AA_AA/_________/"
                        + "_________________/";
            case AUDITORIUM_SEATS:
                return "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_AAAAA_AAAAA_/"
                        + "_____________/"

                        + "_________________/";
            default:
                return "_________________/"
                        + "_________________/";
        }
    }
}
