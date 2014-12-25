/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * LOV Name             LOV ID
    addressDataQuality	 1
    disabilityType       2
    dobDataQuality       3
    employmentType       4
    ethnicity            5
    fiveValDKRefused     6
    fysbReasonNoServices 7
    gender       	 8
    householdType	 9
    housingStatus	 10
    militaryBranch	 11
    monthsHomelessPastThreeYears	12
    nameDataQuality	 13
    noYes                14
    noYesRefused	 15
    pathHowConfirmed	 16
    pathSMIInformation	 17
    projectType          18
    race         	 19
    reasonNotEnrolled	 20
    referralOutcome	 21
    referralSourceSimple 22
    relationshipToHoH	 23
    residencePrior	 24
    residencePriorLengthOfStay	25
    ssnDataQuality	 26
    state                27
    timesHomelessPastThreeYears	28

 * 
 */

    function getLOV(lovType, selectedFieldId){
        if(selectedFieldId.children('option').length === 1){
            $.ajax({
              type:"GET",
              url:"../service/lov/"+lovType,
              data :"",
              dataType:"json",
              restful:true,
              contentType: "application/json",
              cache:false,
              timeout:20000,
              async:true,
              beforeSend :function(xhr) {
                  //xhr.setRequestHeader('Authorization',bearer);
              },
              success:function(data){
                  for(var i = 0; i < data.header.count; i++){
                          selectedFieldId.append($("<option></option>")
                                  .attr("value",data.body[i].value)	
                                  .text(data.body[i].name)); 
                  }
              },
              error:function(data){
                  alert("Error Connecting to the server "+data);
              }
            });	
        }
    }
    
    var selectFieldId;
    $("#NameDataQuality").click(function(){
        selectFieldId =  $("#NameDataQuality");
        getLOV("nameDataQuality",selectFieldId);
    });

    $("#SSNDataQuality").click(function(){
        selectFieldId =  $("#SSNDataQuality");
        getLOV("ssnDataQuality",selectFieldId);
    });

    $("#Race").click(function(){
        selectFieldId =  $("#Race");
        getLOV("race",selectFieldId);
    });

    $("#Ethnicity").click(function(){
        selectFieldId =  $("#Ethnicity");
        getLOV("ethnicity",selectFieldId);
    });

    $("#DOBDataQuality").click(function(){
        selectFieldId =  $("#DOBDataQuality");
        getLOV("dobDataQuality",selectFieldId);
    });
    
    $("#Gender").click(function(){
        selectFieldId =  $("#Gender");
        getLOV("gender",selectFieldId);
    });

    $("#VeteranStatus").click(function(){
        selectFieldId =  $("#VeteranStatus");
        getLOV("fiveValDKRefused",selectFieldId);
    });    
    
    $("#DisablingCondition").click(function(){
        selectFieldId =  $("#DisablingCondition");
        getLOV("fiveValDKRefused",selectFieldId);
    });    
    
    
    $("#ResidencePrior").click(function(){
        selectFieldId =  $("#ResidencePrior");
        getLOV("residencePrior",selectFieldId);
    });
        
        
    $("#ResidencePriorLengthOfStay").click(function(){
        selectFieldId =  $("#ResidencePriorLengthOfStay");
        getLOV("residencePriorLengthOfStay",selectFieldId);
    });            
