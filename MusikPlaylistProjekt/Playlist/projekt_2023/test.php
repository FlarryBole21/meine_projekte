<!DOCTYPE html>
<html>
<head>
	<title>Select Button Background Change Example</title>
	<style>
		body {
			background-color: white;
		}
		
		.blue {
			background-color: blue;
		}
		
		.red {
			background-color: red;
		}
		
		.green {
			background-color: green;
		}
	</style>
</head>
<body>
	<?php
	$color = "";
	if(isset($_POST['color'])){
		$color = $_POST['color'];
	}
	?>
	<form method="post">
		<select name="color" onchange="this.form.submit()">
			<option value="">Select Color</option>
			<option value="blue" <?php if($color == 'blue'){echo 'selected';} ?>>Blue</option>
			<option value="red" <?php if($color == 'red'){echo 'selected';} ?>>Red</option>
			<option value="green" <?php if($color == 'green'){echo 'selected';} ?>>Green</option>
		</select>
	</form>
	<div class="<?php echo $color; ?> box">
		<p>This is a box with a changing background color.</p>
	</div>
</body>
</html>
