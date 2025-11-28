# USERS 테이블

### 회원가입(사용자)(관리자)

INSERT INTO users (userid, email, nickname, password, ufile, createdat)
                VALUES (users_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE);

### 로그인(사용자)(관리자)

SELECT COUNT(*) FROM users WHERE email=? AND password=?;

### 마이페이지(사용자)(관리자)

select userid, email, nickname, ufile, createdat from users where email=?;

### 아이디 찾기(사용자)(관리자)(임시)

SELECT email
FROM users
WHERE nickname = ?;

### 비밀번호 찾기(사용자)(관리자)(임시)

SELECT password
FROM users
WHERE nickname = ? AND email = ?;

### 정보수정(사용자)

UPDATE users
SET email    = ?,
    nickname = ?, 
    password = ?
WHERE email = ?;

### 회원탈퇴(사용자)(관리자)

delete from users where email = ? and password = ?;

### 전체유저 정보확인(관리자)

SELECT userid, email, nickname, ufile, createdat
FROM users
ORDER BY createdat desc;

### 해당 유저 검색(관리자)

<!-- 이메일로 검색 -->
SELECT u.userid,
       u.email,
       u.nickname,
       u.createdat
FROM users u
WHERE u.email LIKE '%' || ? || '%'
ORDER BY u.createdat DESC;


<!-- 닉네임으로 검색 -->
SELECT u.userid,
       u.email,
       u.nickname,
       u.createdat
FROM users u
WHERE u.nickname LIKE '%' || ? || '%'
ORDER BY u.createdat DESC;


# PET 테이블

### 펫 정보 작성(사용자)

INSERT INTO pet (petid, userid, petname, petbreed, birthdate, pettypeid, createdat, ufile)
VALUES (pet_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?);

INSERT INTO pet (petid, userid, petname, petbreed, birthdate, pettypeid, createdat, ufile)
VALUES (pet_seq.NEXTVAL, 1, '겨울이', '페르시안', '2022-06-12', 1, SYSDATE, DEFAULT);

### 펫 페이지(로그인 시 해당유저의 전체펫 조회)(사용자)

SELECT p.petid,
       p.petname,
       p.petbreed,
       p.birthdate,
       p.pettypeid,
       p.createdat,
       p.ufile
FROM pet p
JOIN users u ON p.userid = u.userid
WHERE u.email = ?   -- 로그인한 유저 이메일
ORDER BY p.createdat DESC;

### 전체 펫 페이지(관리자)

SELECT p.petid,
       u.email,
       p.petname,
       p.petbreed,
       p.birthdate,
       p.pettypeid,
       p.createdat,
       p.ufile
FROM pet p
JOIN users u ON p.userid = u.userid
ORDER BY p.createdat DESC;

### 상세페이지(로그인 시 해당유저의 상세펫 조회)(사용자)

SELECT p.petid,
       p.petname,
       p.petbreed,
       p.birthdate,
       p.pettypeid,
       p.createdat,
       p.ufile
FROM pet p
JOIN users u ON p.userid = u.userid
WHERE u.email = ?   -- 로그인한 유저 이메일
  AND p.petid = ?;  -- 선택한 펫 ID

### 펫 정보 수정(사용자)(관리자)

<!-- 사용자 -->
UPDATE pet
SET petname   = ?,
    petbreed  = ?,
    birthdate = ?,
    pettypeid = ?,
    ufile     = ?
WHERE petid   = ?
  AND userid  = (SELECT userid FROM users WHERE email = ?);

<!-- 관리자 -->
UPDATE pet
SET petname   = ?,
    petbreed  = ?,
    birthdate = ?,
    pettypeid = ?,
    ufile     = ?
WHERE petid   = ?;

### 펫 정보 삭제(사용자)(관리자)

<!-- 사용자 -->
DELETE FROM pet
WHERE petid = ?
  AND userid = (SELECT userid FROM users WHERE email = ?);

<!-- 관리자 -->
DELETE FROM pet
WHERE petid = ?;

### 검색 기능(관리자)

#### (이메일)
SELECT p.petid, u.email, p.petname, p.petbreed, p.birthdate, p.pettypeid, p.createdat, p.ufile
FROM pet p
JOIN users u ON p.userid = u.userid
WHERE u.email LIKE '%' || ? || '%'
ORDER BY u.createdat DESC;

#### (펫이름)
SELECT p.petid, u.email, p.petname, p.petbreed, p.birthdate, p.pettypeid, p.createdat, p.ufile
FROM pet p
JOIN users u ON p.userid = u.userid
WHERE p.petname LIKE '%' || ? || '%'
ORDER BY u.createdat DESC;