<template>
  <div>
    <full-calendar
      ref="calendar"
      :config="config"
      :events="events"
    ></full-calendar>
  </div>
</template>
<script>
import { FullCalendar } from 'vue-full-calendar'
import "fullcalendar/dist/fullcalendar.css";
import axios from 'axios'
export default {
  name:'hello',
  components: { FullCalendar },
  data() {
	  	this.user=JSON.parse(localStorage.getItem('User'))
      return {
		events: [],
        config: {
         header:{
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
          },
		  minTime:'07:30',
  		  maxTime:'21:30',
          buttonText: {today: "今天", month: "月", week: "周", day: "日" },
          defaultView: "month", //显示默认视图
		  slotLabelFormat:'HH:mm:ss',// 左侧时间
       },
     }
  },
  mounted:function(){
        	this.checkToken();
            this.getAll();
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
					_this.$router.push({path:'/error'})
        		}
      			})
        	},
            getAll:function(){
                var _this = this;
				console.log(_this.events)
                _this.loading = true;
          	    axios({
            	    url: 'http://localhost:8081/displayUse',
            	    method: 'get',
        		}).then((response) => {
                    _this.loading = false;
        		    _this.list=response.data.data.classUse;
					// 遍历返回的数据
					_this.list.forEach(function(c){
						var json = {
        					title: c.teacherName+"使用"+c.roomName,
        					text: c.teacherName+"使用"+c.roomName,
        					backgroundColor: "#EEC900",
        					start: c.day+' '+c.beginTime,
        					end: c.day+' '+c.endTime
      					};
						// 放入events事件中（在对应的日期上显示）
						_this.events.push(json);
					})
      			})
            },
  }
}
</script>
<style scoped>
</style>

