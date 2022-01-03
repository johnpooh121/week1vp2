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

|Level 1|Level 2|Level 9(Max)|Game Over| 
|:-:|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/86216960/147900163-c38f1132-4931-411b-8a69-c59b1e2b8aa0.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900165-f67810fe-2f91-434d-bf92-4a9547fdb10a.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900146-1a6914ff-a3eb-48ae-9b22-169c8d8eb4e5.png" width="200" />|<img src="https://user-images.githubusercontent.com/86216960/147900413-da977f99-9676-416f-866e-d4f0ea926f4d.png" width="200" />

### 기능
##### Color test(절대 색감)이라는 구글플레이스토어의 미니게임을 구현했습니다.
##### 격자의 색 중에서 나머지와 다른 색을 가진 격자를 터치하면 되는 게임입니다.
##### life는 3이며 stage가 올라갈 때마다 격자의 개수가 늘어납니다.
##### 게임 도중 뒤로가기를 누르거나 강제종료되어도 다시 앱을 실행하면 stage와 최고점수, 남은 life가 저장되어 있습니다.
##### 아래의 토글버튼을 누르면 배경색을 검은색 또는 흰색으로 전환할 수 있습니다.

### 겪었던 시행착오, 기술
##### stage가 올라갈 때마다 격자의 개수를 늘리고 각 격자의 마진과 크기를 관리하는데서 많은 시행착오를 겪다가 GridView로 구현하였습니다.
##### 게임의 phase를 게임 화면과 restart 화면으로 나누어 각각 프래그먼트로 관리하면서 프래그먼트의 생명주기에 대한 기초적인 지식을 쌓을 수 있었습니다.
##### SHaredpreference를 사용해 stage, life, 최고 점수 등을 관리, 저장하였습니다.

#### apk파일은 week1pv2/.apk 입니다
