$(document).ready(function(){
	$("#SubmitQuestionnaireID").on("click",function(event) {
		var Q1 = $('#question1Label').val();
		var Q2 = $('#question2Label').val();
		var Q3 = $('#question3Label').val();
		var Q4 = $('#question4Label').val();
		var Q5 = $('#question5Label').val();
		var Q6 = $('#question6Label').val();
		var Q7 = $('#question7Label').val();
		var Q8 = $('#question8Label').val();
		var Q9 = $('#question9Label').val();
		var Q10 = $('#question10Label').val();
		var Q11 = $('#question11Label').val();
		var Q12 = $('#question12Label').val();
		var Q13 = $('#question13Label').val();
		var Q14 = $('#question14Label').val();
		var Q15 = $('#question15Label').val();
		var Q16 = $('#question16Label').val();
		var Q17 = $('#question17Label').val();
		if(	Q1 != 0){
			if(	Q2 != 0){
				if(	Q3 != 0){
					if(	Q4 != 0){
						if(	Q5 != 0){
							if(	Q6 != 0){
								if(	Q7 != 0){
									if(	Q8 != 0){
										if(	Q9 != 0){
											if(	Q10 != 0){
												if(	Q11 != 0){
													if(	Q12 != 0){
														if(	Q13 != 0){
															if(	Q14 != 0){
																if(	Q15 != 0){
																	if(	Q16 != 0){
																		if(	Q17 != 0){
																			alert("Thank you for your submission");
																		}
																		else{
																			alert("Please select an option");
																		}
																	}
																	else{
																		alert("Please select an option");
																	}
																}
																else{
																	alert("Please select an option");
																}
															}
															else{
																alert("Please select an option");
															}
														}
														else{
															alert("Please select an option");
														}
													}
													else{
														alert("Please select an option");
													}
												}
												else{
													alert("Please select an option");
												}
											}
											else{
												alert("Please select an option");
											}
										}
										else{
											alert("Please select an option");
										}
									}
									else{
										alert("Please select an option");
									}
								}
								else{
									alert("Please select an option");
								}
							}
							else{
								alert("Please select an option");
							}
						}
						else{
							alert("Please select an option");
						}
					}
					else{
						alert("Please select an option");
					}
				}
				else{
					alert("Please select an option");
				}
			}
			else{
				alert("Please select an option");
			}
		}
		else{
			alert("Please select an option");
		}
	})
});