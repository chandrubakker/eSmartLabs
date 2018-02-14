<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/validation" prefix="validation"%>

<!DOCTYPE html>
<html>
	<head>
		<title>eSmartLabs: Create Test</title>
	</head>
	<body>
		<div class="row">
			<div class="col-lg-12 title-col">
				<h1 class="page-title-one text-center">
					Create <small>Test</small>
				</h1>
			</div>
		</div>
		
		<div class="row esm-back-row">
			<div class="col-lg-12 title-col">
				<a href="#" class="btn btn-default btn-sm esm-button esm-back-btn">
					<i class="fa fa-hand-o-left"></i> Back
				</a>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<c:url value="#" var="createTest" />
				<form:form role="form" action="${createTest}" method="POST" modelAttribute="test" id="create-test">
					<form:hidden path="id" />
					<div class="row">
						<div class="col-lg-3">
							<div id="form-group-department-id" class="form-group">
								<label class="control-label">Select Department</label>
								<form:select path="department.id" cssClass="form-control">
									<form:option value="-1">Choose Department</form:option>
									<c:forEach items="${departments}" var="dept">
										<form:option value="${dept.id}">${dept.name}</form:option>
									</c:forEach>
								</form:select>
								<div class="text-danger">
									<form:errors path="department.id" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div id="form-group-name" class="form-group">
								<label class="control-label">Test Name</label>
								<form:input path="name" placeholder="test name"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="name" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div id="form-group-code" class="form-group">
								<label class="control-label">Test Code</label>
								<form:input path="code" placeholder="test code"
									cssClass="form-control" />
								<div class="text-danger">
									<form:errors path="code" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div id="form-group-price" class="form-group">
								<label class="control-label">Test Price</label>
								<div class="input-group">
									<form:input path="price" placeholder="test price"
										cssClass="form-control" aria-describedby="basic-addon2" />
									<span class="input-group-addon" id="basic-addon2">
										<i class="fa fa-inr" aria-hidden="true"></i>
									</span>
								</div>
								<div class="text-danger">
									<form:errors path="price" />
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-4 col-lg-offset-4">
							<div id="form-group-unitType" class="form-group">
								<label class="control-label">Unit Type</label>
								<form:select id="unit_type" path="unitType" cssClass="form-control">
									<form:option value="">Choose Unit Type</form:option>
									<c:forEach items="${unitTypes}" var="unitType">
										<form:option value="${unitType.key}">${unitType.value}</form:option>
									</c:forEach>
								</form:select>
								<div class="text-danger">
									<form:errors path="unitType" />
								</div>
							</div>
						</div>
					</div>	
					
					<div class="row range-sel-row hide">
						<div class="col-lg-8 col-lg-offset-2 deep-col">
							<div class="row">
								<div class="col-lg-6">
									<div id="form-group-rangesSameForAll" class="same-range-radio">
										<label class="control-label">Ranges Same For All?</label>
										
										<label class="radio-inline">
										 	<form:radiobutton path="rangesSameForAll" value="No" />No
										</label>
										
										<label class="radio-inline">
										 	<form:radiobutton path="rangesSameForAll" value="Yes" />Yes
										</label>
										
										<div class="text-danger">
											<form:errors path="rangesSameForAll" />
										</div>
									</div>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-unitUsed" class="form-group">
										<label class="control-label">Unit</label>
										<form:input path="unitUsed"
											placeholder="unit used ex. mg/dl, fl, pg, % etc."
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="unitUsed" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row uni-range-row hide">
						<div class="col-lg-8 col-lg-offset-2 deep-col">
							<div class="row">
								<div class="col-lg-12">
									<h3 class="page-title-two">Universal Ranges</h3>
								</div>
								<div class="col-lg-6">
									<div id="form-group-normalMin" class="form-group">
										<label class="control-label">Min. Value</label>
										<form:input path="normalMin" placeholder="min. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="normalMin" />
										</div>
									</div>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-normalMax" class="form-group">
										<label class="control-label">Max. Value</label>
										<form:input path="normalMax" placeholder="max. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="normalMax" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row diff-range-row hide">
						<div class="col-lg-4 deep-col">
							<div class="row">
								<div class="col-lg-12">
									<h3 class="page-title-two">Male</h3>
								</div>
								<div class="col-lg-6">
									<div id="form-group-maleMin" class="form-group">
										<label class="control-label">Min. Value</label>
										<form:input path="maleMin" placeholder="min. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="maleMin" />
										</div>
									</div>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-maleMax" class="form-group">
										<label class="control-label">Max. Value</label>
										<form:input path="maleMax" placeholder="max. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="maleMax" />
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-lg-4 deep-col">
							<!-- Female Ranges -->
							<div class="row">
								<div class="col-lg-12">
									<h3 class="page-title-two">Female</h3>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-femaleMin" class="form-group">
										<label class="control-label">Min. Value</label>
										<form:input path="femaleMin" placeholder="min. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="femaleMin" />
										</div>
									</div>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-femaleMax" class="form-group">
										<label class="control-label">Max. Value</label>
										<form:input path="femaleMax" placeholder="max. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="femaleMax" />
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-lg-4 deep-col">
							<!-- Children Ranges -->
							<div class="row">
								<div class="col-lg-12">
									<h3 class="page-title-two">Children</h3>
								</div>
								<div class="col-lg-6">
									<div id="form-group-childMin" class="form-group">
										<label class="control-label">Min. Value</label>
										<form:input path="childMin" placeholder="min. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="childMin" />
										</div>
									</div>
								</div>
								
								<div class="col-lg-6">
									<div id="form-group-childMax" class="form-group">
										<label class="control-label">Max. Value</label>
										<form:input path="childMax" placeholder="max. value"
											cssClass="form-control" />
										<div class="text-danger">
											<form:errors path="childMax" />
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<div class="row text-center">
						<div class="col-lg-12">
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-sm esm-button esm-sub-btn">
									<i class="fa fa-save"></i> Create
								</button>
								<button type="reset" class="btn btn-default btn-sm esm-button">
									<i class=" fa fa-refresh "></i> Reset
								</button>
							</div>
						</div>
					</div>
				</form:form>
				<validation:CRUD validateURL="/test/update" requestMethod="POST" busyMessage="creating test..." asynch="false"
						callBackOnSuccess="testCreationSuccess" formId="create-test"
						noSubmit="1" />
			</div>
		</div>
		
		<div class="modal fade" id="test-create-modal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" data-keyboard="false"
			data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Success</h4>
					</div>
					<div class="modal-body custom-modal-body"></div>
					<div class="modal-footer">
						<a href="<c:url value="/test/create"/>" class="btn btn-info"> Add New</a>
						<a href="<c:url value="/tests"/>" class="btn btn-success"> Close</a>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		
			function testCreationSuccess() {
				
				var name = $("#name").val();
				$(".custom-modal-body").text(name + " - Created Successfully.");
				$("#test-create-modal").modal('show');
			}
		
			function RadionButtonSelectedValueSet(name, SelectdValue) {
			    
				console.log("YESSING");
				$('input[name="' + name+ '"][value="' + SelectdValue + '"]').prop('checked', true);
			}
			
			$(document).ready(function() {
				
				$("#unit_type").on('change', function() {
					
					console.log("SEL: " + this.value);
					
					if($('input[name=rangesSameForAll]:checked', '#create-test').val() === "No")
						RadionButtonSelectedValueSet('rangesSameForAll', 'Yes');
					
					if(this.value === "ranges") {
						
						if($(".range-sel-row").hasClass('hide'))
							$(".range-sel-row").removeClass("hide");
						
						if($(".uni-range-row").hasClass('hide'))
							$(".uni-range-row").removeClass('hide');
						
						if(!$(".diff-range-row").hasClass('hide'))
							$(".diff-range-row").addClass('hide');
					} else if(this.value === "neg_pos") {
						
						if(!$(".range-sel-row").hasClass('hide'))
							$(".range-sel-row").addClass("hide");
						
						if(!$(".uni-range-row").hasClass('hide'))
							$(".uni-range-row").addClass('hide');
						
						if(!$(".diff-range-row").hasClass('hide'))
							$(".diff-range-row").addClass('hide');
					} else {
						
						if(!$(".range-sel-row").hasClass('hide'))
							$(".range-sel-row").addClass("hide");
						
						if(!$(".uni-range-row").hasClass('hide'))
							$(".uni-range-row").addClass('hide');
						
						if(!$(".diff-range-row").hasClass('hide'))
							$(".diff-range-row").addClass('hide');
					}
					
				});
				
				$('input[type=radio][name=rangesSameForAll]').change(function() {
			        
					console.log("Radio Value: " + this.value);
					if (this.value == 'Yes') {
			            
						if($(".uni-range-row").hasClass('hide'))
							$(".uni-range-row").removeClass('hide');
						
						if(!$(".diff-range-row").hasClass('hide'))
							$(".diff-range-row").addClass('hide');
			        } else if (this.value == 'No') {
			            
			        	if(!$(".uni-range-row").hasClass('hide'))
							$(".uni-range-row").addClass('hide');
						
						if($(".diff-range-row").hasClass('hide'))
							$(".diff-range-row").removeClass('hide');
			        }
			    });
				
				$('button[type=reset]').click(function(e) {
					
					if(!$(".range-sel-row").hasClass('hide'))
						$(".range-sel-row").addClass("hide");
					
					if(!$(".uni-range-row").hasClass('hide'))
						$(".uni-range-row").addClass('hide');
					
					if(!$(".diff-range-row").hasClass('hide'))
						$(".diff-range-row").addClass('hide');
				});
			});
		</script>			
	</body>
</html>