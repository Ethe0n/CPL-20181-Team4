# CPL-20181-Team4
종합 설계 프로젝트  2

## 민들레
- 심리 상담 챗봇
- AI 서버와 웹 서버로 구분
### 웹 서버 실행
- [Node.js](https://nodejs.org/ko/), [Python 3](https://www.python.org/) 설치 필요
1. 명령 프롬프트 실행
1. `./DevWeb` 폴더로 이동
1. `npm install` 입력
1. `npm run build` 입력
1. `npm start` 입력
### 웹 서버 개발
1. `.\watch-back [!]` - 백엔드(서버) 측 변경 사항 감지하여 자동 빌드
    - `!`를 입력하면 배포 모드로 1회 빌드
1. `.\watch-front page [!]` - 프론트엔드(클라이언트) 측 변경 사항 감지하여 자동 빌드
    - `page` - 감지할 한 페이지. `*`를 입력하면 모든 페이지(느려질 수 있음)
    - `!`를 입력하면 배포 모드로 1회 빌드
