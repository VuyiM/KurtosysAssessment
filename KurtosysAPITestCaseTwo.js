import axios from 'axios';
import { error } from 'selenium-webdriver';

const startTime = new Date().getTime();

// using the GET method a call is made to the url
axios.get('https://www.kurtosys.com').then(response =>{
     if (response.status == 200){
        console.log("API call OK with 200 status");

        //Checking the response time only for the successful API call
        const endTime = new Date().getTime();
        const responseTime = endTime - startTime;
        if (responseTime<2000){
            console.log("The response time is ", responseTime)
            console.log("Acceptable response time");
        }
        else{
            console.log("Response time is TOO LONG!");
            console.log("The response time is ", responseTime)
        }
     }
     else{
        console.log("API call FAILED");
     }

})
.catch(error=>{
    console.error("There was an error with your call. See :", error);
})

