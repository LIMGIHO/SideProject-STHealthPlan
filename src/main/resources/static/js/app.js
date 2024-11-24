document.addEventListener("DOMContentLoaded", function() {
    const defaultCheckbox = document.getElementById("defaultProgram");
    const exerciseProgramSelect = document.getElementById("exerciseProgram");

    defaultCheckbox.addEventListener("change", function() {
        const isChecked = defaultCheckbox.checked;
        const selectedProgramId = exerciseProgramSelect.value;
        console.log("defaultCheckbox", isChecked);
        if (selectedProgramId !== "default") {
            fetch('/saveDefaultProgram', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    programId: selectedProgramId,
                    def: isChecked
                }),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        } else {
            alert("Please select a program first.");
            defaultCheckbox.checked = false;
        }
    });
});
