# 몰입캠프 1주차 (자유주제 : 절대 색감 게임)

**팀원 : [공병규](https://github.com/johnpooh121), [최준영](https://github.com/DDoubleA)**

## 프로젝트 개요
```
3개의 탭으로 구성된 앱
Tab1 - 연락처
Tab2 - 갤러리 & 사진 추가/확대 기능
Tab3 - 절대 색감 게임
```

## 탭 1) 연락처


|First Screen|Detail information|add contacts|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/147900138-c61f22ff-3a7e-495b-b7a2-1ad86633744c.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900139-7f3f2f15-7f7c-453f-8a37-1ee6a273a987.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900423-7802bf4e-8d55-4312-94b5-fcbeaf6a9b36.png" width="200"/>
 
### 기능
 ##### JSON 형식을 이용해서 임의의 연락처 데이터를 구축하였고 이를 받아와 읽은 뒤 ListView를 통해 화면에 출력하였습니다. 
 ##### 연락처를 누르면 해당 연락처에 대한 세부 정보를 포함한 화면이 출력됩니다.
 ##### 오른쪽 밑에 floatingbutton을 클릭하면 연락처를 추가할 수 있는 화면이 나오고 전화번호와 이름 모두를 입력해야 추가하기 버튼이 반응합니다.
 
 ### 겪었던 시행착오, 기술

##### JSON 형식의 파일에  사진도 함께 저장하고 싶었습니다. -> JSON 파일에 사진의 이름만 저장하고 이후 화면에 출력할 때 사진이 저장된 경로로 찾아가 해당 사진의 이름으로 사진을 찾아 출력했습니다.

##### listview를 사용해 연락처를 구현하였고 fragement-activity, activity-activity 간 정보 전달하는 법을 배웠습니다.




## 탭 2) 갤러리

|First Screen|Full Screen|Zoom In|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/147900147-c7a023d4-ec8c-4206-b1b6-a5b5d54a64e4.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900151-bed62847-0a8a-4f08-97d2-c86c2656ace4.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900160-afda13f4-5ab9-4267-ade2-5fe48d3fd608.png" width="200" />|

### 기능
##### 갤러리의 사진을 누르면 새 액티비티가 실행되어 전체화면으로 누른 사진을 볼 수 있습니다.
##### 전체화면 상태에서 드래그로 줌인/줌아웃이 가능합니다.
##### 아래의 + 버튼을 누르면 새 사진을 추가할 수 있으며 다중선택이 가능합니다.

|Selection Mode|Multiple Choice|New image loaded|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/96766204/147900870-b13ad3f4-edc7-4439-a146-37720536c98c.png" width="200" />|<img src="https://user-images.githubusercontent.com/96766204/147900851-b71057ac-a193-4166-bf13-c46a3d20c01d.png" width="200" />|<img src="https://user-images.githubusercontent.com/96766204/147900852-00ef4e43-4457-4d7c-b179-6bf1c9af8289.png" width="200" />|

### 겪었던 시행착오, 기술
##### 사진의 용량이 큰 경우 랙이 걸리거나 앱이 강제종료 되어서 Glide와 thumbnail기능을 이용해 용량을 줄였습니다
##### 리사이클러뷰를 이용해 갤러리를 구현했습니다
##### 인텐트를 이용해 다중선택 기능을 구현할 수 있었고 이미지를 uri로 관리하는 방법을 알게 되었습니다

## 탭 3) 절대색감 게임

|Level 1|Level 2|Level 9(Max)|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/148019722-f4585f34-4189-4531-b681-d6bc4568bf23.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019723-b490b633-3c1f-4bbf-80f0-37b476488c89.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019671-a345289f-f10d-46e5-9c81-9e3829ef0393.png" width="200" />|

### 기능
##### Color test(절대 색감)이라는 구글플레이스토어의 미니게임을 구현했습니다.
##### 격자의 색 중에서 나머지와 다른 색을 가진 격자를 터치하면 되는 게임입니다.
##### life는 3이며 stage가 올라갈 때마다 격자의 개수가 늘어납니다.
##### 게임 도중 뒤로가기를 누르거나 강제종료되어도 다시 앱을 실행하면 stage와 최고점수, 남은 life가 저장되어 있습니다.
##### 아래의 토글버튼을 누르면 배경색을 검은색 또는 흰색으로 전환할 수 있습니다.
|Game over|answer position|color difference|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/148019261-80812f74-724a-4261-bfd4-453695a25bc5.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019264-3a3176c7-ded1-499a-b983-91b13719b37e.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019265-6fa4b04d-2b73-4217-8fcd-1ab560091b48.png" width="200" />|

##### life가 0이 되어 게임이 종료되면 아래에 "Continue", "눌러보세요" 버튼이 생기고, 배경색이 기본 색으로 변하게 됩니다.
##### "눌러보세요" 버튼을 한번 클릭하면 정답의 위치, 한번 더 클릭하면 배경 색(BG)과 바뀐 색(COL)의 RGB 값을 볼 수 있습니다.
##### Continue 버튼을 클릭하면 시작 화면이 나오며 EASY MODE와 HARD MODE를 선택해 새 게임을 시작할 수 있습니다.
##### Easy Mode에서와 다르게 Hard Mode에서는 BG와 COL간의 차이가 더 적어집니다.
|Debug Mode|Debug Mode2|Continue|
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/148019267-91a3d51a-bb24-456f-936c-8a423057637d.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019257-b049c7b2-0d39-4efb-bae3-15c6c6b6ed37.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/148019260-cacdab74-2720-43f9-9f5f-0583e3e62c8d.png" width="200" />|

##### Life가 0이 되었을 때 Continue 버튼을 누르지 않고 Debug Mode에서 게임을 진행할 수 있도록 만들었습니다.
##### Debug Mode에서는 stage가 계속 올라가지만 maxscore는 업데이트 되지 않습니다.


### 겪었던 시행착오, 기술
##### stage가 올라갈 때마다 격자의 개수를 늘리고 각 격자의 마진과 크기를 관리하는데서 많은 시행착오를 겪다가 GridView로 구현하였습니다.
##### 게임의 phase를 게임 화면과 restart 화면으로 나누어 각각 프래그먼트로 관리하면서 프래그먼트의 생명주기에 대한 기초적인 지식을 쌓을 수 있었습니다.
##### Sharedpreference를 사용해 stage, life, 최고 점수,mode 등을 관리, 저장하였습니다.
##### 뒤로가기를 눌렀다가 다시 앱을 실행하면 오류가 발생해서 알아보다가 프래그먼트 내에서는 getChildFragmentManager() 로 프래그먼트 매니저를 불러온다는 것을 알게되었습니다.

#### apk파일은 week1pv2/color_sense_twomode.apk 입니다
