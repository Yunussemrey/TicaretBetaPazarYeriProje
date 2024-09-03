

import pymssql


server = 'DELL-VOSTRO\SQLEXPRESS'  # Örnein, 'localhost' veya '192.168.1.100'
database = 'ERP'
username = 'sa'
password = '1'


conn = pymssql.connect(server, username, password, database)

try:
    cursor = conn.cursor()


    cursor.execute("select * from w_shareket_stok_mus_bar")


    rows = cursor.fetchall()

    with open ("C:\Test\Ödev_2.txt","w",encoding="utf-8") as file:
        for row in rows:
            file.write(str(row) +  '\n')


    print ("View dosyası oluşturuldu.")

except pymssql.DatabaseError as hata:

    print(f"veritabanı hatası:{hata} ")

finally:

    conn.close()

