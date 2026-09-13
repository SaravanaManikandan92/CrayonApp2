package com.crayon.constants;

import java.util.*;

public class Constants {

    //

    public static List<String> clientList = Arrays.asList("test","05c4379e-9d9b-4170-add9-df499540084c","987ad23c-2031-420e-9992-7491a93551be");
  //  public static List<String> clientList = Arrays.asList("Magically_Genius","Outbox_Labs","MicroSaaS_Labs","Cheap_Inbox");
  public static Map<String,String> clientListMap = Map.ofEntries(
          Map.entry("05c4379e-9d9b-4170-add9-df499540084c", "Magically_Genius"),
          Map.entry("987ad23c-2031-420e-9992-7491a93551be","Mailin-Ai")

  );
  // public static String INFLUX_TOKEN ="Kq7seBQiRWyqbkF4DYe5mH-6FkVoyW6xTXF5LzGsBihqTpkULkXBfLhYhtyyBA9tdgTN3SPrDeitO6on-_cEdA==";//"Token A7MJiqEvG-3B-n5geDnVWQ_61KnpWbLpueTPywlr7PrsX19YTqXyikVuTnjnshDOEVv9-1wUa7XSMbSnxjm0Tw=="; //this is local

    public static String INFLUX_TOKEN ="my-super-token";//;"Kq7seBQiRWyqbkF4DYe5mH-6FkVoyW6xTXF5LzGsBihqTpkULkXBfLhYhtyyBA9tdgTN3SPrDeitO6on-_cEdA=="; //this is prod
    //public static String INFLUX_ORG ="SaraOrg";//"f086a9856ff86826" // //AxelOrg //"SaraOrg" //this is local
    public static String INFLUX_ORG ="AxelOrg";
    public static String INFLUX_BUCKET ="CrayonAudit";
    public static String INFLUX_URL ="http://localhost:8086";
    public static String INFLUX_URL_QUERY ="http://localhost:8086/api/v2/query";
    public static String INFLUX_URL_WRITE ="http://localhost:8086/api/v2/write";

    public static int ORGANIZATION_ID= 4059442;
    public static int PUBLISHER_ID=2;
    public static int Magically_Genius_INVOICE_ID=207443;
    public static int Outbox_Labs_INVOICE_ID=180723;
    public static int MicroSaaS_Labs_INVOICE_ID=211425;
    public static int Cheap_Inbox_INVOICE_ID=212451;
    public static int Axalin_INVOICE_ID=203480;
    public static int  Receptive_Marketing=213628;

    public static int cancelSubscription=4;

    public static Map<String,String> invoiceMap = Map.ofEntries(
            Map.entry("05c4379e-9d9b-4170-add9-df499540084c", ""),
            Map.entry("987ad23c-2031-420e-9992-7491a93551be","227126")
    );//203480

    public static final Map<Integer, Integer> baseMonthDateMap = Map.ofEntries(
            Map.entry(1, 31),
            Map.entry(2, 28), // default February
            Map.entry(3, 31),
            Map.entry(4, 30),
            Map.entry(5, 31),
            Map.entry(6, 30),
            Map.entry(7, 31),
            Map.entry(8, 31),
            Map.entry(9, 30),
            Map.entry(10, 31),
            Map.entry(11, 30),
            Map.entry(12, 31)
    );


    //https://api.crayon.com/api/
    public enum ClientDetails {

        CLIENT_ID("CLIENT_ID", "5681b3ec-640f-4a55-974d-3de2e8edcccf"),
        CLIENT_SECRET("CLIENT_SECRET", "36a1ad5b-581a-4389-85f1-7326c2545a3c"),
        USER_NAME("USER_NAME", "rajasekar@fugotek.co.in"),
        PASS_WORD("PASS_WORD", "Fugotek@2026"),
        GRANT_TYPE("","password"),
        SCOPE("","CustomerApi"),
        CRAYON_BASE_URL("CRAYON_BASE_URL","https://api.crayon.com/api/"),
        CRAYON_BASE_URL_HTTP("CRAYON_BASE_URL_HTTP","http://api.crayon.com/api/"),
        CRAYON_TOKEN_API_URL("CRAYON_TOKEN_API_URL", "v1/connect/token"),
        CRAYON_TOKEN_CREATE_TENANT_API_URL("CRAYON_TOKEN_CREATE_TENANT_API_URL","v1/customertenants/"),
        CRAYON_TOKEN_CONSENT_AGREEMENT_URL("","v1/customertenants/"),
        CRAYON_TOKEN_ASSIGN_AGREEMENT_URL("","v1/subscriptions/"),
        CRAYON_TOKEN_ASSIGN_AGREEMENT_BY_NEW_COMMERCE_URL("","v1/subscriptions/new-commerce-orders"),
        CRAYON_GET_TENANT_BY_ID("","v1/customertenants/"),
        CRAYON_GET_SUBSCRIPTION_BY_ID("","v1/subscriptions/"),
        CRAYON_GET_SUBSCRIPTION_BY_TENANT_ID("","v1/subscriptions?OrganizationId=value1&customerTenantId=value2")
        ;


        private String key;
        private String value;

        ClientDetails(final String key, final String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


}
