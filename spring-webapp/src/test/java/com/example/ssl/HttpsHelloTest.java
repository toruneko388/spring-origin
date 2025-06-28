package com.example.ssl;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString; 

class HttpsHelloTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "https://appserver";
        RestAssured.port     = 8443;
        // 開発用の自己署名は信用しないのでバリデーションを緩める
        RestAssured.useRelaxedHTTPSValidation(); // これにより、自己署名証明書を使用している場合でも、HTTPSリクエストが成功するようになります
    }

    @Test
    void helloEndpointShouldReturn200() {
        given() // リクエストの前提条件（今回は特に無し）
        .when().get("/spring-webapp/hello") // 指定したパスに GET リクエストを送る
        .then().statusCode(200) // レスポンスのステータスコードが 200 であることを確認
               .body(containsString("Thymeleaf!")); // レスポンスのボディが "Thymeleaf!" であることを確認
    }
}
