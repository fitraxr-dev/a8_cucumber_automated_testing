Feature: My Course

    Background:
        Given User telah login dengan kredesial yang valid
        And User berada di halaman Dashboard

    Scenario: Course Dalam Progress
        When User klik tombol kursus saya
        Then User diarahkan ke halaman kursus saya tab Dalam Progress
        And Halaman menampilkan daftar kursus yang sedang berlangsung