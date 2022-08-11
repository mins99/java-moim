package com.woowahan.moim.member.acceptance;

import static com.woowahan.moim.login.acceptance.LoginAcceptanceTest.로그인_요청;
import static org.assertj.core.api.Assertions.assertThat;

import com.woowahan.moim.AcceptanceTest;
import com.woowahan.moim.login.domain.TokenResponse;
import com.woowahan.moim.member.application.dto.MemberRequest;
import com.woowahan.moim.member.application.dto.MemberResponse;
import com.woowahan.moim.member.application.dto.MemberUpdateRequest;
import com.woowahan.moim.member.application.dto.OrganizerMemberRequest;
import com.woowahan.moim.member.application.dto.ParticipantMemberRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class MemberAcceptanceTest extends AcceptanceTest {

    public static final String organizerUser = "organizer";
    public static final String participantUser = "participant";
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
        ExtractableResponse<Response> response = 주최자를_생성한다(validPassword);

        // then
        회원_생성됨(response);
    }

    @DisplayName("주최자 생성시 암호 규칙에 맞지 않으면 생성되지 않는다")
    @Test
    void createOrganizer_fail() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_생성한다(invalidPassword);

        // then
        회원_생성_실패(response);
    }

    @DisplayName("이미 가입한 주최자로 생성시 생성되지 않는다")
    @Test
    void createOrganizer_fail2() {
        // given & when
        ExtractableResponse<Response> response = 주최자를_생성한다(validPassword);

        // then
        회원_생성됨(response);

        // given & when
        ExtractableResponse<Response> response2 = 주최자를_생성한다(validPassword);

        // then
        회원_생성_실패(response2);
    }

    @DisplayName("주최자로 생성된 회원의 참여자 정보를 추가한다")
    @Test
    void updateParticipant() {
        // given
        주최자를_생성한다(validPassword);
        String 로그인_토큰 = 로그인_요청(organizerUser, validPassword).as(TokenResponse.class).getToken();

        // when
        ExtractableResponse<Response> response = 참여자를_추가한다(로그인_토큰);

        // then
        회원정보_추가됨(response);
    }

    @DisplayName("참여자를 생성한다")
    @Test
    void createParticipant() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_생성한다(validPassword);

        // then
        회원_생성됨(response);
    }

    @DisplayName("참여자 생성시 암호 규칙에 맞지 않으면 생성되지 않는다")
    @Test
    void createParticipant_fail() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_생성한다(invalidPassword);

        // then
        회원_생성_실패(response);
    }

    @DisplayName("이미 가입한 참여자로 생성시 생성되지 않는다")
    @Test
    void createParticipant_fail2() {
        // given & when
        ExtractableResponse<Response> response = 참여자를_생성한다(validPassword);

        // then
        회원_생성됨(response);

        // given & when
        ExtractableResponse<Response> response2 = 참여자를_생성한다(validPassword);

        // then
        회원_생성_실패(response2);
    }

    @DisplayName("참여자로 생성된 회원의 주최자 정보를 추가한다")
    @Test
    void updateOrganizer() {
        // given
        참여자를_생성한다(validPassword);
        String 로그인_토큰 = 로그인_요청(participantUser, validPassword).as(TokenResponse.class).getToken();

        // when
        ExtractableResponse<Response> response = 주최자를_추가한다(로그인_토큰);

        // then
        회원정보_추가됨(response);
    }

    @DisplayName("회원 정보를 조회한다")
    @Test
    void findMemberInfo() {
        // given
        주최자를_생성한다(validPassword);
        String 로그인_토큰 = 로그인_요청(organizerUser, validPassword).as(TokenResponse.class).getToken();

        // when
        ExtractableResponse<Response> response = 회원정보를_조회한다(로그인_토큰);

        // then
        회원정보_조회됨(response, organizerUser);
    }

    @DisplayName("회원 정보를 수정한다")
    @Test
    void updateMemberInfo() {
        // given
        주최자를_생성한다(validPassword);
        String 로그인_토큰 = 로그인_요청(organizerUser, validPassword).as(TokenResponse.class).getToken();
        MemberResponse 수정전_회원정보 = 회원정보를_조회한다(로그인_토큰).as(MemberResponse.class);

        // when
        ExtractableResponse<Response> response = 회원정보를_수정한다(로그인_토큰);

        // then
        MemberResponse 수정후_회원정보 = 회원정보를_조회한다(로그인_토큰).as(MemberResponse.class);
        회원정보_수정됨(response, 수정전_회원정보, 수정후_회원정보);
    }

    public static ExtractableResponse<Response> 주최자를_생성한다(String password) {
        MemberRequest memberRequest = new MemberRequest("홍길동", LocalDate.of(1999, 01, 01), "M", organizerUser, password, "test@gmail.com", "team");

        return doPostNoAuth("/members/organizer", memberRequest);
    }

    public static ExtractableResponse<Response> 참여자를_생성한다(String password) {
        MemberRequest memberRequest = new MemberRequest("홍길동", LocalDate.of(1999, 01, 01), "M", participantUser, password, "test@gmail.com", "1,2,3,4", "안녕하세요");

        return doPostNoAuth("/members/participant", memberRequest);
    }

    public static ExtractableResponse<Response> 주최자를_추가한다(String loginToken) {
        OrganizerMemberRequest organizerMemberRequest = new OrganizerMemberRequest("team");

        return doPut(loginToken, "/members/organizer", organizerMemberRequest);
    }

    public static ExtractableResponse<Response> 참여자를_추가한다(String loginToken) {
        ParticipantMemberRequest participantMemberRequest = new ParticipantMemberRequest("1,2,3,4", "안녕하세요");

        return doPut(loginToken, "/members/participant", participantMemberRequest);
    }

    public static ExtractableResponse<Response> 회원정보를_조회한다(String loginToken) {
        return doGet(loginToken, "/members/me");
    }

    public static ExtractableResponse<Response> 회원정보를_수정한다(String loginToken) {
        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("홍길동2", LocalDate.of(1990, 11, 11), "F", validPassword, "test2@gmail.com", "team2");

        return doPut(loginToken, "/members/me", memberUpdateRequest);
    }

    public static void 회원_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 회원정보_추가됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 회원_생성_실패(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static void 회원정보_조회됨(ExtractableResponse<Response> response, String userId) {
        MemberResponse memberResponse = response.as(MemberResponse.class);
        assertThat(memberResponse.getId()).isNotNull();
        assertThat(memberResponse.getUserId()).isEqualTo(userId);
    }

    public static void 회원정보_수정됨(ExtractableResponse<Response> response, MemberResponse 수정전_회원정보, MemberResponse 수정후_회원정보) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(수정전_회원정보.getBirthday()).isNotEqualTo(수정후_회원정보.getBirthday());
        assertThat(수정전_회원정보.getEmail()).isNotEqualTo(수정후_회원정보.getEmail());
        assertThat(수정전_회원정보.getGender()).isNotEqualTo(수정후_회원정보.getGender());
        assertThat(수정전_회원정보.getUserId()).isEqualTo(수정후_회원정보.getUserId());
    }
}
