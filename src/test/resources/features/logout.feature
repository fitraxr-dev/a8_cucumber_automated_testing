Feature: Logout Sebagai Pelajar

    Scenario: Proses logout manual – role Pelajar
        Given User sudah login sebagai Pelajar dan sesi aktif
        When User mengklik dropdown Nama Akun pada navbar
        And User mengklik sub-menu Keluar
        Then User berhasil logout dan diarahkan ke halaman login
        And User tidak dapat mengakses halaman yang membutuhkan autentikasi
