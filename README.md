# 질문 삭제하기
## 요구사항
1. 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    * 데이터의 삭제 상태를 표현
2. 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    * isOwner(loginUser)
3. 답변이 없는 경우 삭제가 가능하다.
4. 질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.
    * deleteAnswers(replyUser)
5. 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
    * changeDeletedStatus(deleted)
6. 질문자와 답변자가 다른 경우 답변을 삭제 할 수 없다.
    * delete(replyUser)
7. 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
    * deleteHistories.add()
    * DeleteHistories.addAll()
    
## 프로그래밍 요구사항
1. qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
2. 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.


# 볼링 점수판(점수계산)
## 기능 요구사항
사용자 1명의 볼링 게임 점수를 관리할 수 있는 프로그램을 구현한다.
1. Frame 클래스에서 점수를 구한다.
    * 스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다.
    * 스페어는 다음 1번의 투구까지 점수를 합산해야 한다.
2. ResultView : 노멀프레임(1~9), 파이널프레임(10) 점수 출력

## 프로그래밍 요구사항
* 객체지향 5원칙을 지키면서 프로그래밍한다.
1. SRP (단일책임의 원칙: Single Responsibility Principle)
    작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
2. OCP (개방폐쇄의 원칙: Open Close Principle)
    소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
3. LSP (리스코브 치환의 원칙: The Liskov Substitution Principle)
    서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
4. ISP (인터페이스 분리의 원칙: Interface Segregation Principle)
    한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
5. DIP (의존성역전의 원칙: Dependency Inversion Principle)
    구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.

# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)