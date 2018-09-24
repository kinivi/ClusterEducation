package com.projects.deus_ex_machina.clustereducation;

public class PollResult {

    public String value1;
    public String value2;
    public String comment1;
    public String comment2;

    public PollResult() {
        // Конструктор по умолчанию. Необходим для работы Firebase
    }

    public PollResult(String value1, String value2, String comment1, String comment2) {
        this.value1 = value1;
        this.value2 = value2;
        this.comment1 = comment1;
        this.comment2 = comment2;
    }
}
