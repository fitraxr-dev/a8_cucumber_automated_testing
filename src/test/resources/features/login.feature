Feature: Login Sebagai Pelajar

    Scenario: User bisa login dengan kredensial yang valid
        Given User berada di halaman login
        When User memasukkan email valid: "fitra.pelajar@example.com" 
        And User memasukkan password valid: "Fitra.pelajar123"
        Then User berhasil login dan diarahkan ke halaman dashboard