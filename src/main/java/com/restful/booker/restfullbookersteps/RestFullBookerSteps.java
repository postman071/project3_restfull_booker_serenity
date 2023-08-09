package com.restful.booker.restfullbookersteps;

import com.restful.booker.constants.Path;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.model.TokenPojo;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class RestFullBookerSteps {

    @Step("Create token for user")
    public Response createToken() {
        TokenPojo tokenPojo = TokenPojo.getTokenPojo();
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(tokenPojo)
                .post(Path.AUTHENTICATE);
    }

    @Step("Create booking information with firstName: {0}, lastName : {1}, price : {2}, deposit : {3}, additionalNeeds : {4} and token : {5}")
    public Response createNewBooking(String firstName, String lastName, int price, boolean deposit, String additionalNeeds, String token) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, price, deposit, additionalNeeds);
        return SerenityRest.given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .post(Path.BOOKING);
    }

    @Step("Update booking information with firstName: {0}, lastName : {1}, price : {2}, deposit : {3}, additionalNeeds : {4} and token : {5},token : {6}")
    public Response updateNewBooking(String firstName, String lastName, int price, boolean deposit, String additionalNeeds, String token, int bookingid) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, price, deposit, additionalNeeds);
        return SerenityRest.given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .put(Path.BOOKING + "/" + bookingid);
    }

    @Step("Get Booking by bookingId bookingid : {0}")
    public Response getBookingId(int bookingid, String token) {
        return SerenityRest.given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .get(Path.BOOKING + "/" + bookingid);
    }

    @Step("Delete Booking by bookingId bookingid : {0}")
    public Response deleteBookingId(int bookingid, String token) {
        return SerenityRest.given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .delete(Path.BOOKING + "/" + bookingid);
    }


}

