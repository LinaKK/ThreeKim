//로그인 성공시 값을 넘겨줄 class

package com.example.studyforce;

import android.content.Context;

public class Login_Success_info {

    //로그인 성공시 받아올 학번
    public int userid2 = ((Login_Request)Login_Request.context_login).userid1;


    //나 intent 사용 안했어. 그냥 int 변수(=학번)만 사용해도 될것같아서.. 만약 int제외 다른 형태 변수가 필요하면 intent로 전환 시켜도 돼!!!
    //이거는 activity에서 acitvity로 int변수만 넘길 때 사용하는 방법이래!!!

}
