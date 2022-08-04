package com.woowahan.moim.login.acceptance;

import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.invalidPassword;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.validPassword;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.주최자를_추가한다;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.참여자를_추가한다;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.woowahan.moim.AcceptanceTest;
import com.woowahan.moim.login.domain.TokenRequest;
import com.woowahan.moim.login.domain.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class LoginAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();

        주최자를_추가한다(validPassword);
        참여자를_추가한다(validPassword);
    }

    @DisplayName("주최자 로그인")
    @Test
    void organizer_login() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청("organizer", validPassword);

        // then
        로그인_성공(response);
    }

    @DisplayName("주최자 로그인시 비밀번호가 틀리면 로그인 실패")
    @Test
    void organizer_login_fail() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청("organizer", invalidPassword);

        // then
        로그인_실패(response);
    }

    @DisplayName("참여자 로그인")
    @Test
    void participant_login() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청("participant", validPassword);

        // then
        로그인_성공(response);
    }

    @DisplayName("참여자 로그인시 비밀번호가 틀리면 로그인 실패")
    @Test
    void participant_login_fail() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청("participant", invalidPassword);

        // then
        로그인_실패(response);
    }

    public static ExtractableResponse<Response> 로그인_요청(String userId, String password) {
        TokenRequest tokenRequest = new TokenRequest(userId, password);

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(tokenRequest)
                .when().post("/login")
                .then().log().all()
                .extract();
    }

    private void 로그인_성공(ExtractableResponse<Response> response) {
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.as(TokenResponse.class).getToken()).isNotEmpty()
        );
    }

    private void 로그인_실패(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
