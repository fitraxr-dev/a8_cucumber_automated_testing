Feature: Login Sebagai Pelajar

    @author-amr
    Scenario: User bisa login dengan kredensial yang valid
        Given User berada di halaman login
        When User memasukkan email valid: "fitra.pelajar@example.com" 
        And User memasukkan password valid: "Fitra.pelajar123"
        And User klik tombol login
        Then User berhasil login dan diarahkan ke halaman dashboard

    Scenario: Email dan Password Kosong
        Given User berada di halaman login
        When User langsung klik login
        Then Sistem menampilkan pesan "Login gagal, email dan kata sandi harus diisi"