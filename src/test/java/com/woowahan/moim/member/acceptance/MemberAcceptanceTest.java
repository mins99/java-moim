package com.woowahan.moim.member.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.woowahan.moim.AcceptanceTest;
import com.woowahan.moim.member.application.dto.MemberRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class MemberAcceptanceTest extends AcceptanceTest {

    public static final String validPassword = "!@#qwe123";
    public static final String invalidPassword = "1234";

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @DisplayName("주최자를 생성한다")
    @Test
    void createOrganizer() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_추가한다(validPassword);

        // then
        회원_생성됨(response);
    }

    @DisplayName("주최자 생성시 암호 규칙에 맞지 않으면 생성되지 않는다")
    @Test
    void createOrganizer_fail() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_추가한다(invalidPassword);

        // then
        회원_생성_실패(response);
    }

    @DisplayName("이미 가입한 주최자로 생성시 생성되지 않는다")
    @Test
    void createOrganizer_fail2() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_추가한다(validPassword);

        // then
        회원_생성됨(response);

        // given & when
        ExtractableResponse<Response> response2 = 주최자를_추가한다(validPassword);

        // then
        회원_생성_실패(response2);
    }

    @DisplayName("참여자를 생성한다")
    @Test
    void createParticipant() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_추가한다(validPassword);

        // then
        회원_생성됨(response);
    }

    @DisplayName("참여자 생성시 암호 규칙에 맞지 않으면 생성되지 않는다")
    @Test
    void createParticipant_fail() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_추가한다(invalidPassword);

        // then
        회원_생성_실패(response);
    }

    @DisplayName("이미 가입한 참여자로 생성시 생성되지 않는다")
    @Test
    void createParticipant_fail2() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_추가한다(validPassword);

        // then
        회원_생성됨(response);

        // given & when
        ExtractableResponse<Response> response2 = 참여자를_추가한다(validPassword);

        // then
        회원_생성_실패(response2);
    }

    public static ExtractableResponse<Response> 주최자를_추가한다(String password) {
        MemberRequest memberRequest = new MemberRequest("홍길동", "19990101", 'M', "organizer", password, "test@gmail.com", "team");

        return doPostNoAuth("/members/organizer", memberRequest);
    }

    public static ExtractableResponse<Response> 참여자를_추가한다(String password) {
        MemberRequest memberRequest = new MemberRequest("홍길동", "19990101", 'M', "participant", password, "test@gmail.com", "1,2,3,4", "안녕하세요");

        return doPostNoAuth("/members/participant", memberRequest);
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 회원_생성_실패(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
