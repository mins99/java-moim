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

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @DisplayName("주최자를 생성한다")
    @Test
    void createOrganizer() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_추가한다();

        // then
        회원_생성됨(response);
    }

    @DisplayName("참여자를 생성한다")
    @Test
    void createParticipant() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_추가한다();

        // then
        회원_생성됨(response);
    }

    public static ExtractableResponse<Response> 주최자를_추가한다() {
        MemberRequest memberRequest = new MemberRequest("홍길동", "19990101", 'M', "organizer", "!@#abc123", "test@gmail.com", "team");

        return doPostNoAuth("/members/organizer", memberRequest);
    }

    public static ExtractableResponse<Response> 참여자를_추가한다() {
        MemberRequest memberRequest = new MemberRequest("홍길동", "19990101", 'M', "participant", "!@#abc123", "test@gmail.com", "1,2,3,4", "안녕하세요");

        return doPostNoAuth("/members/participant", memberRequest);
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
