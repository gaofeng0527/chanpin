// gradle clean build -Denv=production
environments {
    development {
        jdbc {
            driverClassName = 'com.mysql.jdbc.Driver'
            url = 'jdbc:mysql://127.0.0.1:3306/toolkit?useUnicode=true&amp&characterEncoding=UTF-8'
            username = 'root'
            password = ''
        }

        redis {
            hostName = 'localhost'
            port = 6379
            password = ''
        }

        logBase = "D:/360安全浏览器下载/microApp/logs"
    }

    production {
        jdbc {
            driverClassName = 'com.mysql.jdbc.Driver'
            url = 'jdbc:mysql://127.0.0.1:3306/cp_kit?useUnicode=true&amp&characterEncoding=UTF-8'
            username = 'root'
            password = 'tiger_sun'
        }

        redis {
            hostName = '172.16.1.41'
            port = 6379
            password = '123456'
        }

        logBase = '/data/cp/logs'

    }
}
