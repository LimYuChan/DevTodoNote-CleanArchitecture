## DevTodoNote

## Github repository별 Todo List를 관리 할 수 있는 앱 입니다.

### 기술스택

* Kotlin
* MVVM
* Clean Architecture
* Dagger Hilt
* Jetpack Navigation component
* Room Database
* Lottie
* Retrofit

### 로그인

|로그인 화면|Github 로그인|
|:---:|:---:|
|![login](./screenshot/login.jpg)|![github_login](./screenshot/github_login.jpg)|


### Github Repository 가져오기
##### 자신의 github repository를 가져온 후 repository를 클릭하여 Todo Note를 작성 할 수 있습니다.

<p align="center"><img src="./screenshot/repository.jpg" width="50%"/></p>

### Repository별 Todo Note 리스트
##### 작성한 Todo list를 All, Todo, Done 으로 확인 할 수 있습니다. 

<p align="center"><img src="./screenshot/todo_list.jpg" width="50%"/></p>


### Todo Note 작성
##### 해야 할 일을 작성 할 수 있습니다. 단순하게 메모만 작성하는 것이 아닌 참고 할만한 이미지, 간단한 아이디어 스케치, 참고 할 링크를 추가 할 수 있습니다.
##### (Todo 작성 시 왼쪽 하단에 보이는 브렌치명으로 브렌치를 생성하고 해당 브렌치를 commit, merge 시 자동 트리거)

|작성 화면|이미지 선택 화면|아이디어 스케치 추가|
|:---:|:---:|:---:|
|![todo_write](./screenshot/todo_write.jpg)|![todo_select_image](./screenshot/todo_select_image.jpg)|![todo_drawing_doard](./screenshot/todo_drawing_doard.jpg)|

### Todo Viewer
##### 작성한 Todo를 완료한 후 티켓에 들어가면 자동으로 작업 내역을 파악하여 해당 브렌치의 상태를 자동으로 업데이트 합니다.

|Todo 티켓 자세히보기 화면|Todo 티켓 자동으로 Merge 스테이트로 변경된 화면|Todo List에서 자동으로 Done으로 넘어간 화면|
|:---:|:---:|:---:|
|![todo_viewer](./screenshot/todo_viewer.jpg)|![todo_merge_viewer](./screenshot/todo_merge_viewer.jpg)|![todo_auto_done](./screenshot/todo_auto_done.jpg)|
