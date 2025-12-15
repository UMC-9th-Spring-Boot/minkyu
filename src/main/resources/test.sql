-- Food Category (3개)
INSERT INTO food_category (category, created_at, updated_at) VALUES
('한식', NOW(), NOW()),
('일식', NOW(), NOW()),
('양식', NOW(), NOW());

-- Member (3명)
INSERT INTO member (name, email, phone_number, gender, birth_date, address, status, points, password, role, created_at, updated_at) VALUES
('홍길동', 'user1@test.com', '010-1111-1111', 'MALE', '1990-01-01', '서울시 강남구', true, 0, '1234', 'USER', NOW(), NOW()),
('김철수', 'user2@test.com', '010-2222-2222', 'MALE', '1992-02-02', '서울시 서초구', true, 0, '1234', 'USER', NOW(), NOW()),
('이영희', 'user3@test.com', '010-3333-3333', 'FEMALE', '1994-03-03', '서울시 송파구', true, 0, '1234', 'USER', NOW(), NOW());

-- Region (2개)
INSERT INTO region (porvince_name, city_name, member_id, created_at, updated_at) VALUES
('서울시', '강남구', 1, NOW(), NOW()),
('서울시', '송파구', 3, NOW(), NOW());

-- Store (3개) - score는 Integer 타입이므로 정수로 입력
INSERT INTO store (name, region_id, food_category_id, score, created_at, updated_at) VALUES
('강남 맛집', 1, 1, 4, NOW(), NOW()),
('강남 스시', 1, 2, 5, NOW(), NOW()),
('송파 카페', 2, 3, 4, NOW(), NOW());

-- Review (각 가게당 2-3개씩)
INSERT INTO review (rating, content, member_id, shop_id, created_at, updated_at) VALUES
-- 강남 맛집 리뷰 (3개)
(5, '인생 맛집입니다.', 1, 1, DATE_SUB(NOW(), INTERVAL 1 DAY), NOW()),
(4, '맛있는데 비싸요.', 2, 1, DATE_SUB(NOW(), INTERVAL 2 DAY), NOW()),
(5, '강추!', 3, 1, DATE_SUB(NOW(), INTERVAL 3 DAY), NOW()),

-- 강남 스시 리뷰 (2개)
(5, '신선해요.', 1, 2, DATE_SUB(NOW(), INTERVAL 1 HOUR), NOW()),
(4, '깔끔해요.', 2, 2, DATE_SUB(NOW(), INTERVAL 2 HOUR), NOW()),

-- 송파 카페 리뷰 (3개)
(5, '커피 맛집.', 1, 3, DATE_SUB(NOW(), INTERVAL 3 HOUR), NOW()),
(4, '디저트 맛집.', 2, 3, DATE_SUB(NOW(), INTERVAL 4 HOUR), NOW()),
(5, '조용함.', 3, 3, DATE_SUB(NOW(), INTERVAL 5 HOUR), NOW());
