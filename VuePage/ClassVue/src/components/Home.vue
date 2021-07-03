<template>
	<el-container  class="home_container">
		<el-header class="header">
				<ul class="nav">
					<li class="hover">
						<i class="el-icon-s-home" style="margin-right:7px"></i>
						<router-link to="/home">首页</router-link>
					</li>
					<li>
						<i class="el-icon-edit-outline" style="margin-right:7px"></i>
						<router-link to="/Apply">申请</router-link>
					</li>
				</ul>
				<ul class="nav">
					<li>
						<router-link to="/admin">管理员</router-link>
					</li>
					<el-dropdown trigger="click" @command="handleCommand">
					    <span class="el-dropdown-link">
					      个人中心<i class="el-icon-arrow-down el-icon--right"></i>
					    </span>
					    <template #dropdown>
							<el-dropdown-menu>
							  <el-dropdown-item  command="a" icon="el-icon-circle-plus-outline" >退出</el-dropdown-item>
							</el-dropdown-menu>
					    </template>
					</el-dropdown>
				</ul>
		</el-header>
		<el-main class="main">
			<router-view></router-view>
		</el-main>
		
	</el-container>	
</template>
<script>

	import axios from 'axios'
	import qs from 'qs'

	export default {
		data(){
			this.user=JSON.parse(localStorage.getItem('User'))
			return {
      				
    		}
		},
		mounted:function(){
        	this.checkToken();
	  	},
		methods: {
        	checkToken:function(){
          	var _this = this;
          	axios({
            	url: 'http://localhost:8081/checkToken',
            	method: 'get',
            	headers: {
              		token: _this.user.token
          		}
        		}).then((response) => {
        			console.log(response.data)
        		if(!response.data){
          			console.log('校验失败')
					// 去到校验信息失败页面
          			_this.$router.push({path:'/error'})
        		}
      			})
        	},
			handleCommand(command) {
        		this.$confirm('是否退出登录', '提示', {
         		confirmButtonText: '确定',
          		cancelButtonText: '取消',
          		type: 'warning'
        	}).then(() => {
					// 清空token
					localStorage.clear();
					// 跳转到登陆页面
					this.$router.push('/')
          			this.$message({
            		type: 'success',
            		message: '您已成功退出登录!'
          			});
        		}).catch(() => {
          			this.$message({
            		type: 'info',
            		message: '已取消退出登录'
          			});          
        		});
			}
      	}
	}
</script>

<style>
	
	.home_container {
	  height: 100%;
	  position: absolute;
	  top: 0px;
	  left: 0px;
	  width: 100%;
	  overflow:auto;
	}
	.header {
		width: 100%;
   		opacity: 0.9;
    	font-size: 15px;
		background-color: rgba(0,0,0,1);
	  	display: flex;
	  	align-items: center;
	}
	.header li{
		background: rgba(255,255,255,.15);
    	color: #fff!important;
		line-height: 60px;
		float: left;
		margin-left: 50px;
		padding: 0 15px;
		font-size: 16px;
		cursor: pointer;
		border-radius:10px 10px 10px 10px; 
    list-style: none;
	}
	.header li>a {
		background: 0 0;
    	color: rgba(255,255,255,.9);
      text-decoration:none
	}
	.header .el-dropdown{
		height: 60px;
		line-height: 60px;
		float: left;
		margin-left: 25px;
		padding: 0 15px;
		font-size: 16px;
		cursor: pointer;
		
	}
	.header .el-dropdown .el-dropdown-link {
		color: #fff;
	}
	
	.header li:hover{
		background-color: #604E6E;
	}

	


</style>
