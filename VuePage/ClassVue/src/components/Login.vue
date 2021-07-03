<template>
  <el-form :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.inputName" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="loginForm.inputPassWord" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary"  @click.native.prevent="submitClick($event)" id="1" style="width: 40%">教师登录</el-button>
      <el-button type="primary"  @click.native.prevent="submitClick($event)" id="2" style="width: 40%">管理员登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import axios from 'axios'
  import qs from 'qs'
  import JSON from 'JSON'
 
  export default{
    data(){
      return {
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
        loginForm: {
          inputName: '',
          inputPassWord: '',
          type: ''
        },
        loading: false
      }
    },
    methods: {
      submitClick: function (a) {
        //alert(localStorage.getItem('User'))
        var type=a.currentTarget.id
        var _this = this;
        this.loading = true;
        axios.post("http://localhost:8081/doLogin",qs.stringify({
          inputName: this.loginForm.inputName,
          inputPassWord: this.loginForm.inputPassWord,
          type
        })).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            //成功
            if(type==1){
              // 将当前用户存入session域中
              var user = JSON.stringify(resp.data.data.teacher);
              localStorage.setItem('User',user);
              console.log(user)

              if (resp.data.code == 200) {
              _this.$router.replace({path: '/home'});
            } else {
              _this.$alert('登录失败!', '失败!');
            }
            }else if(type==2){
              
              var user = JSON.stringify(resp.data.data.admin);
              localStorage.setItem('User',user);
              console.log(user)
              
              if (resp.data.code == 200) {
              _this.$router.replace({path: '/home'});
            } else {
              _this.$alert('登录失败!', '失败!');
            }
            }
          } 
          else {
            //失败
            _this.$alert('登录失败!', '失败!');
          }
        }, resp=> {
          _this.loading = false;
          _this.$alert('用户名或密码错误!', '失败!');
        });
      }
    }
  }
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
