package com.woowahan.moim.login.acceptance;

import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.invalidPassword;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.organizerUser;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.participantUser;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.validPassword;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.주최자를_생성한다;
import static com.woowahan.moim.member.acceptance.MemberAcceptanceTest.참여자를_생성한다;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.woowahan.moim.AcceptanceTest;
import com.woowahan.moim.login.domain.TokenRequest;
import com.woowahan.moim.login.domain.TokenResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class LoginAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();

        주최자를_생성한다(validPassword);
        참여자를_생성한다(validPassword);
    }

    @DisplayName("주최자 로그인")
    @Test
    void organizer_login() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청(organizerUser, validPassword);

        // then
        로그인_성공(response);
    }

    @DisplayName("주최자 로그인시 비밀번호가 틀리면 로그인 실패")
    @Test
    void organizer_login_fail() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청(organizerUser, invalidPassword);

        // then
        로그인_실패(response);
    }

    @DisplayName("참여자 로그인")
    @Test
    void participant_login() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청(participantUser, validPassword);

        // then
        로그인_성공(response);
    }

    @DisplayName("참여자 로그인시 비밀번호가 틀리면 로그인 실패")
    @Test
    void participant_login_fail() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청(participantUser, invalidPassword);

        // then
        로그인_실패(response);
    }

    @DisplayName("존재하지 않는 계정 정보로 로그인시 로그인 실패")
    @Test
    void no_user_login_fail() {
        // given & when
        ExtractableResponse<Response> response = 로그인_요청("test", validPassword);

        // then
        로그인_실패(response);
    }

    public static ExtractableResponse<Response> 로그인_요청(String userId, String password) {
        TokenRequest tokenRequest = new TokenRequest(userId, password);

        return doPostNoAuth("/login", tokenRequest);
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
