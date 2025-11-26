# USERS 테이블

### 회원가입

INSERT INTO users (userid, email, nickname, password, ufile, createdat)
                VALUES (users_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE);
                
INSERT INTO users (userid, email, nickname, password, createdat)
                VALUES (users_seq.NEXTVAL, 'iis@naver.com', 'gg', '123', SYSDATE);

### 로그인

SELECT COUNT(*) FROM users WHERE email=? AND password=?;

SELECT COUNT(*) FROM users WHERE email='iis@naver.com' AND password='123';

### 마이페이지

select userid, email, nickname, ufile, createdat from users where email=?;

select userid, email, nickname, ufile, createdat from users where email='iis@naver.com';

### (사용자) 정보수정

UPDATE users
SET email    = ?,
    nickname = ?, 
    password = ?
WHERE email = ?;

UPDATE users
SET email    = 'iis55@naver.com',
    nickname = 'qwer', 
    password = '1234'
WHERE email = 'iis@naver.com';

### 회원탈퇴

delete from users where email = ? and password = ?;

delete from users where email = 'iis55@naver.com' and password = '1234';

# PET 테이블