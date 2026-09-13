package com.crayon.constants;

import java.util.*;

public class Constants {

    //

    public static List<String> clientList = Arrays.asList("test","57747f5d-6758-4afa-9d65-0a3097b68389","dd636335-efbe-491b-b3b3-d8225c8dc1d1",
            "6434afd2-811c-400c-ac13-d133b476086a","30b1b92c-cd3c-46c2-8375-de13cb5d20ae","1d742172-6ea3-46d0-b83e-9553c5879414",
            "36ef3b68-aae4-4521-b367-8cefeff85b29","999668d6-5b49-4ea3-aa2a-00856183c0e9","1543c674-58d3-41c9-8af7-56eb6e8e4306");
  //  public static List<String> clientList = Arrays.asList("Magically_Genius","Outbox_Labs","MicroSaaS_Labs","Cheap_Inbox");
  public static Map<String,String> clientListMap = Map.ofEntries(
          Map.entry("57747f5d-6758-4afa-9d65-0a3097b68389", "Magically_Genius"),
          Map.entry("dd636335-efbe-491b-b3b3-d8225c8dc1d1", "Outbox_Labs"), // default February
          Map.entry("6434afd2-811c-400c-ac13-d133b476086a", "MicroSaaS_Labs"),
          Map.entry("30b1b92c-cd3c-46c2-8375-de13cb5d20ae", "Cheap_Inbox"),
          Map.entry("1d742172-6ea3-46d0-b83e-9553c5879414","axalin"),
          Map.entry("36ef3b68-aae4-4521-b367-8cefeff85b29","Receptive_Marketing"),
          Map.entry("999668d6-5b49-4ea3-aa2a-00856183c0e9","Mailin-Ai"),
          Map.entry("1543c674-58d3-41c9-8af7-56eb6e8e4306","INBOX navigator")
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
            Map.entry("57747f5d-6758-4afa-9d65-0a3097b68389", "207443"),
            Map.entry("dd636335-efbe-491b-b3b3-d8225c8dc1d1", "180723"), // default February
            Map.entry("6434afd2-811c-400c-ac13-d133b476086a", "211425"),
            Map.entry("30b1b92c-cd3c-46c2-8375-de13cb5d20ae", "212451"),
            Map.entry("1d742172-6ea3-46d0-b83e-9553c5879414","203480"),
            Map.entry("36ef3b68-aae4-4521-b367-8cefeff85b29","213628"),
            Map.entry("999668d6-5b49-4ea3-aa2a-00856183c0e9","222776"),
            Map.entry("1543c674-58d3-41c9-8af7-56eb6e8e4306","229274")
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

        CLIENT_ID("CLIENT_ID", "bd131176-82c8-49cf-9c01-387d7ae11a80"),
        CLIENT_SECRET("CLIENT_SECRET", "cbebba0a-d33f-4957-a796-d90c6ab06e81"),
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
