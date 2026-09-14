package com.crayon.service;

import com.crayon.constants.Constants;
import com.crayon.model.*;
import com.crayon.utility.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class CrayonService {

    private static final Logger logger = LoggerFactory.getLogger(CrayonService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TokenCache tokenCache;

    public CreateCustomerTenantResponse createTenant(CreateCustomerTenantRequest createCustomerTenantRequest) {
        try {
            InvoiceProfile invoiceProfile=new InvoiceProfile();
            invoiceProfile.setId(Integer.parseInt(Constants.invoiceMap.get(createCustomerTenantRequest.getSource())));
            createCustomerTenantRequest.getTenant().setInvoiceProfile(invoiceProfile);
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue() +Constants.ClientDetails.CRAYON_TOKEN_CREATE_TENANT_API_URL.getValue();
            Utility.generateAndSetTrackingId(createCustomerTenantRequest);
            Utility.logHere(createCustomerTenantRequest,"request",null,null);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(createCustomerTenantRequest.getSource()));
            HttpEntity<CreateCustomerTenantRequest> request = new HttpEntity<>(createCustomerTenantRequest, httpHeaders);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(request);
            System.out.println("Request JSON: " + json);
            ResponseEntity<CreateCustomerTenantResponse> response = restTemplate.postForEntity(url, request, CreateCustomerTenantResponse.class);
            CreateCustomerTenantResponse createCustomerTenantResponse=response.getBody();
            Utility.setTrackingIdAndSource(createCustomerTenantRequest.getSource(),createCustomerTenantRequest.getTrackingId(),createCustomerTenantResponse);
            Utility.logHere(createCustomerTenantRequest,"response",createCustomerTenantResponse,null);
            return createCustomerTenantResponse ;
        } catch (Exception ex) {
            Utility.logHere(createCustomerTenantRequest,"exception",null,ex.getMessage());
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }



    public ConsentAgreement consentAgreement(String id,ConsentAgreement consentAgreement) {
        try {
            String url =Constants.ClientDetails.CRAYON_BASE_URL.getValue()+ Constants.ClientDetails.CRAYON_TOKEN_CONSENT_AGREEMENT_URL.getValue();
            url=url+id+"/agreements";
            Utility.generateAndSetTrackingId(consentAgreement);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(consentAgreement.getSource()));
            HttpEntity<ConsentAgreement> request = new HttpEntity<>(consentAgreement, httpHeaders);
            //Log here
            Utility.logHere(id,consentAgreement,"request",null);
            ResponseEntity<ConsentAgreement> response = restTemplate.postForEntity(url, request, ConsentAgreement.class);
            ConsentAgreement response_=response.getBody();
            Utility.setTrackingIdAndSource(consentAgreement.getSource(),consentAgreement.getTrackingId(),response_);
            Utility.logHere(id,response_,"response",null);
            return response_;
        } catch (Exception ex) {
            Utility.logHere(id,consentAgreement,"exception",ex.getMessage());
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }


    public AssignedSubscriptionResponse createAssignment(AssignSubscription assignSubscription) {
        try {
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_TOKEN_ASSIGN_AGREEMENT_URL.getValue();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(assignSubscription.getSource()));
            HttpEntity<AssignSubscription> request = new HttpEntity<>(assignSubscription, httpHeaders);
            Utility.generateAndSetTrackingId(assignSubscription);
            //Log here
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(request);
            System.out.println("Request JSON: " + json);
            Utility.logHere(assignSubscription,"request",null,null);
            ResponseEntity<AssignedSubscriptionResponse> response = restTemplate.postForEntity(url, request, AssignedSubscriptionResponse.class);
            AssignedSubscriptionResponse assignedSubscriptionResponse= response.getBody();
            Utility.setTrackingIdAndSource(assignSubscription.getSource(),assignSubscription.getTrackingId(),assignedSubscriptionResponse);
            Utility.logHere(assignSubscription,"response",assignedSubscriptionResponse,null);
            return assignedSubscriptionResponse;
        } catch (Exception ex) {
            Utility.logHere(assignSubscription,"exception",null,ex.getMessage());
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }

    public SubscriptionUpdate updateSubscription(String id,SubscriptionUpdate updateSubscription,boolean cancel) {
        try {
            if(cancel)
            {
                updateSubscription.setStatus(Constants.cancelSubscription);
            }
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_TOKEN_ASSIGN_AGREEMENT_URL.getValue()+id;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(updateSubscription.getSource()));
            HttpEntity<SubscriptionUpdate> request = new HttpEntity<>(updateSubscription, httpHeaders);
            Utility.generateAndSetTrackingId(updateSubscription);
            //Log here
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(request);
            System.out.println("Request JSON: " + json);
            Utility.logHere(updateSubscription,"request",null,null);
         ;
            ResponseEntity<SubscriptionUpdate> response =    restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    SubscriptionUpdate.class
            );
            SubscriptionUpdate subscriptionUpdateResp= response.getBody();
            Utility.setTrackingIdAndSource(updateSubscription.getSource(),updateSubscription.getTrackingId(),subscriptionUpdateResp);
            Utility.logHere(updateSubscription,"response",subscriptionUpdateResp,null);
            return subscriptionUpdateResp;
        } catch (Exception ex) {
            Utility.logHere(updateSubscription,"exception",null,ex.getMessage());
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }


    public AssignSubscriptionByNewCommerceResponse createAssignmentByNewCommerceId(AssignSubscriptionByNewCommerce assignSubscription) {
        try {
            String url =  Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_TOKEN_ASSIGN_AGREEMENT_BY_NEW_COMMERCE_URL.getValue();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(assignSubscription.getSource()));
            HttpEntity<AssignSubscriptionByNewCommerce> request = new HttpEntity<>(assignSubscription, httpHeaders);
            Utility.generateAndSetTrackingId(assignSubscription);
            //Log here
            Utility.logHere(assignSubscription,"request",null,null);
            ResponseEntity<AssignSubscriptionByNewCommerceResponse> response = restTemplate.postForEntity(url, request, AssignSubscriptionByNewCommerceResponse.class);
            AssignSubscriptionByNewCommerceResponse assignedSubscriptionResponse= response.getBody();
            Utility.setTrackingIdAndSource(assignSubscription.getSource(),assignSubscription.getTrackingId(),assignedSubscriptionResponse);
            Utility.logHere(assignSubscription,"response",assignedSubscriptionResponse,null);
            return assignedSubscriptionResponse;
        } catch (Exception ex) {
            Utility.logHere(assignSubscription,"exception",null,ex.getMessage());
            throw new InternalException("Something went wrong while assigning  the new commerceid " + ex.getMessage());
        }
    }

    public Tenant getTenantById(String id,String source) {
        try {
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_GET_TENANT_BY_ID.getValue();
            url=url+id;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(source));
            //Log here
            Utility.logHere(source,id,null,"request",null);
            HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<Tenant> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, Tenant.class);
            Tenant response_=response.getBody();
            Utility.logHere(source,id,response_,"response",null);
            return response_;
        } catch (Exception ex) {
            Utility.logHere(source,id,null,"exception",null);
            throw new InternalException("Something went wrong while getTenantById " + ex.getMessage());
        }
    }


    public SubscriptionResponse getSubscriptionById(String id, String source) {
        try {
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_GET_SUBSCRIPTION_BY_ID.getValue();
            url=url+id;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(source));
            //Log here
            Utility.logGetSubscriptionById(source,id,null,"request",null);
            HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<SubscriptionResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, SubscriptionResponse.class);
            SubscriptionResponse response_=response.getBody();
            Utility.logGetSubscriptionById(source,id,response_,"response",null);
            return response_;
        } catch (Exception ex) {
            Utility.logGetSubscriptionById(source,id,null,"exception",null);
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }
    }

    public ListOfSubscriptionResponse getSubscriptionByTenantId(String id,String source) {
        try {
            String url = Constants.ClientDetails.CRAYON_BASE_URL.getValue()+Constants.ClientDetails.CRAYON_GET_SUBSCRIPTION_BY_TENANT_ID.getValue();
            url=url.replace("value1",String.valueOf(Constants.ORGANIZATION_ID));
            url=url.replace("value2",id);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON); // No charset needed
            httpHeaders.add("Authorization", "Bearer " + tokenCache.getToken(source));
            //Log here
            Utility.logHere(source,id,null,"request",null);
            HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<ListOfSubscriptionResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, ListOfSubscriptionResponse.class);
            ListOfSubscriptionResponse response_=response.getBody();
            Utility.logGetSubscriptionListById(source,id,response_,"response",null);
            return response_;
        } catch (Exception ex) {
           ex.printStackTrace();
            Utility.logGetSubscriptionListById(source,id,null,"response",null);
            throw new InternalException("Something went wrong while getTenantById " + ex.getMessage());
        }
    }


    public List<CreateCustomerTenantReqResp> GetCreateCustomerTenantReqRespBySource(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInCreateCustomerTenantReqResp(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }


    public List<ConsentAgreementReqResp> GetBySourceInConsentAgreementReqResp(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInConsentAgreementReqResp(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }


    public List<AssignSubscriptionReqResp> GetBySourceInAssignSubscriptionReqResp(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInAssignSubscriptionReqResp(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }

    public List<UpdateSubscriptionLogResponse> GetBySourceInUpdateSubscriptionReqResp(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInUpdateSubscriptionReqResp(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }


    public List<AssignSubscriptionByNewCommerceLogResponse> GetBySourceInAssignSubscriptionNewCommerceReqResp(String source, String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInAssignSubscriptionNewCommerceReqResp(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }
    }


    public List<TenantResponse> GetBySourceInGetByTenantId(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInGetByTenantId(source,trackingId,date,month,year);
        } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }

    public List<SubscriptionLogResponse> getBySourceInGetBySubscriptionId(String source,String trackingId,String date,String month,String year) {
        try {
            return Utility.getBySourceInGetBySubscriptionId(source,trackingId,date,month,year);
         } catch (Exception ex) {
            throw new InternalException("Something went wrong while creating the tenant " + ex.getMessage());
        }

    }



}
