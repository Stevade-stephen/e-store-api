package com.stevade.userservice.query.api.projection;

import com.stevade.commonservice.models.CardDetails;
import com.stevade.commonservice.models.User;
import com.stevade.commonservice.queries.GetUserPaymentDetailsQuery;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    public User getUserPaymentDetails(GetUserPaymentDetailsQuery paymentDetailsQuery){
        //Ideally, the database should be queried for the details
        CardDetails cardDetails = CardDetails.builder()
                .name("Stephen")
                .validUntilMonth(11)
                .validUntilYear(2034)
                .cardNumber("55773399001188")
                .cvv(234)
                .build();

       return User.builder()
                .userId(paymentDetailsQuery.getUserId())
                .cardDetails(cardDetails)
                .firstName("Stephen")
                .lastName("Adehor")
                .build();
    }
}
