<template>
<div class="applyForm" v-loading="loading">
    <h2 style="text-align:center">教室使用申请</h2>
    <span style="margin-left:5%">申请人：</span>{{user.teacherName}}
    <div style="margin-top:3%">
        <span style="margin-left:5%">教室：</span>
        <el-select v-model="roomName" placeholder="请选择教室">
            <el-option
      				v-for="item in roomlist"
      				:key="item.id"
					    :label="item.roomName"
      				:value="item.id">
    		</el-option>
        </el-select>
    </div>
    <div style="margin-top:3%">
        <span style="margin-left:5%">申请时间：</span>
        <el-date-picker type="dates" placeholder="选择日期" v-model="dayTime" value-format="yyyy-MM-dd" style="width: 70%"></el-date-picker>
        <br>
        <span style="margin-left:5%">开始上课：</span>
        <el-select v-model="beginTime" placeholder="开始时间" style="width:150px;margin-top:3%">
            <el-option
      				v-for="item in schedulelist"
      				:key="item.beginTime"
					    :label="item.beginTime"
      				:value="item.beginTime">
    		</el-option>
        </el-select>
        
        <span style="margin-left:5%">结束上课：</span>
        <el-select v-model="endTime" placeholder="结束时间" style="width:150px;margin-top:3%">
            <el-option
      				v-for="item in schedulelist"
      				:key="item.endTime"
					    :label="item.endTime"
      				:value="item.endTime">
    		</el-option>
        </el-select>
    </div>
  <el-button type="primary"  style="width:22% ;margin-left:40%;margin-top:3%;margin-bottom:3%"
			@click="applyRoom">申请</el-button>
</div>

</template>
<script>
import axios from 'axios'
import qs from 'qs'
import {isNotNullORBlank} from '../utils/utils'
  export default {
    data() {
        // 从session中取出登录时存放的 User 的值
        this.user=JSON.parse(localStorage.getItem('User'))
        console.log(this.user)
      return {
        roomlist: [],
        schedulelist: [],
        loading: false,
        dayTime:'',
        beginTime: '',
        endTime: '',
        roomName:'',
        rid: '',
      };
    },
    mounted:function(){
		    this.RoomList();
        this.ScheduleList();
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
        //console.log(response.data)
        if(!response.data){
          console.log('校验失败')
          _this.$router.push({path:'/error'}) 
        }
      })
        },
        //页面加载时，发送查找全部教室的请求
      	RoomList:function(){
        		var that=this;
        		axios.get("http://localhost:8081/room/allRoom")
        		.then(function(response){
					//console.log(response.data.data.room)
					that.roomlist=response.data.data.room;
					//console.log(that.roomlist)
				})
      	},
        ScheduleList:function(){
            var that=this;
        		axios.get("http://localhost:8081/schedule")
        		.then(function(response){
					//console.log(response.data.data.schedule)
					that.schedulelist=response.data.data.schedule;
					//console.log(that.schedulelist)
				})
        },
        applyRoom:function(){
          var _this = this;
	      	if (!(isNotNullORBlank(_this.roomName, _this.dayTime, _this.beginTime,_this.endTime))) {
          			this.$message({type: 'error', message: '数据不能为空!'});
          			return;
        	}
          this.$confirm('确认申请?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let data={
              tid: _this.user.id,
          		rid: _this.roomName,
				      dayTime: _this.dayTime,
          		beginTime: _this.beginTime,
          		endTime: _this.endTime,}
              console.log(data)
              axios.post("http://localhost:8081/setClassUse",data)
                .then(resp=>{
          		      _this.loading = false;
				            //console.log(resp.data.code,resp.data.msg)
				          if (resp.data.code == 200 && resp.data.msg == '处理成功！') {
					            _this.$message({type: 'success', message: '已成功申请!'});
					            _this.$router.push({
						            path: '/home',
            			      name: 'Home',
				          	})
				          }else if(resp.data.code == 100){
                      console.log(resp.data.msg)
                      this.$message({
                        type: 'error',
                        message: resp.data.msg,
                      })
                  }
			          }).catch(() => {
                    this.$message({
                    type: 'info',
                    message: '已取消申请'
                  }); 
          });         
        });
      }
    }
  };
</script>
<style>
    .applyForm{
        margin-left:25%;
        width: 50%;
        margin-top:5%;
        box-shadow: 0 0 25px #cac6c6;
        text-align:left;
    }
</style>