document.addEventListener('DOMContentLoaded', () => {
    const amountInput = document.getElementById('amount');
    const durationInput = document.getElementById('duration');
    const monthlyInput = document.getElementById('monthlyPayment'); // Updated ID
    const projectSelect = document.getElementById('project');
    const occupationSelect = document.getElementById('occupation'); // Updated name to 'occupation'

    const step1Form = document.getElementById('step-1-form');
    const step2Form = document.getElementById('step-2-form');
    const step3Form = document.getElementById('step-3-form');

    const step1Indicator = document.getElementById('step-1-indicator');
    const step2Indicator = document.getElementById('step-2-indicator');
    const step3Indicator = document.getElementById('step-3-indicator');

    const amountRange = document.getElementById('amount-range');
    const durationRange = document.getElementById('duration-range');
    const monthlyRange = document.getElementById('monthly-range');

    const formGroup = document.querySelector('.form-group');
    const monthlyLabel = document.createElement('div');
    monthlyLabel.classList.add('monthly-label');
    formGroup.appendChild(monthlyLabel);

    const interestRate = 0.05;

    function calculateMonthly(amount, duration) {
        if (!amount || !duration) return 0;
        const monthlyRate = interestRate / 12;
        const monthly = (amount * monthlyRate * Math.pow(1 + monthlyRate, duration)) / (Math.pow(1 + monthlyRate, duration) - 1);
        return isNaN(monthly) || monthly <= 0 ? 0 : monthly.toFixed(2);
    }


    function updateMonthlyLabel(value) {
        monthlyLabel.textContent = `${value} DH`;
        const rangeWidth = monthlyRange.offsetWidth;
        const thumbPosition = (monthlyRange.value - monthlyRange.min) / (monthlyRange.max - monthlyRange.min) * rangeWidth;
        monthlyLabel.style.left = `${thumbPosition}px`;
    }

    function syncInputs(input, range, callback) {
        input.addEventListener('input', () => {
            range.value = input.value;
            callback();
        });
        range.addEventListener('input', () => {
            input.value = range.value;
            callback();
        });
    }

    function updateDurationBasedOnMonthly() {
        const amount = parseFloat(amountInput.value);
        const monthly = parseFloat(monthlyInput.value);
        const monthlyRate = interestRate / 12;

        if (monthly <= 0 || amount <= 0) {
            durationInput.value = 0;
            durationRange.value = 0;
            return;
        }

        const duration = Math.log(monthly / (monthly - amount * monthlyRate)) / Math.log(1 + monthlyRate);
        durationInput.value = Math.round(duration);
        durationRange.value = durationInput.value;
    }

    function updateMonthlyWhenAmountOrDurationChanges() {
        const amount = parseFloat(amountInput.value);
        const duration = parseFloat(durationInput.value);
        const monthlyValue = calculateMonthly(amount, duration);

        if (monthlyValue) {
            monthlyInput.value = monthlyValue;
            monthlyRange.value = monthlyValue;
            updateMonthlyLabel(monthlyValue);
        }
    }

    syncInputs(amountInput, amountRange, updateMonthlyWhenAmountOrDurationChanges);
    syncInputs(durationInput, durationRange, updateMonthlyWhenAmountOrDurationChanges);

    syncInputs(monthlyInput, monthlyRange, updateDurationBasedOnMonthly);

    function goToStep(stepNumber) {
        step1Form.style.display = (stepNumber === 1) ? 'block' : 'none';
        step2Form.style.display = (stepNumber === 2) ? 'block' : 'none';
        step3Form.style.display = (stepNumber === 3) ? 'block' : 'none';

        step1Indicator.classList.toggle('active', stepNumber === 1);
        step2Indicator.classList.toggle('active', stepNumber === 2);
        step3Indicator.classList.toggle('active', stepNumber === 3);
    }


    step1Form.addEventListener('submit', (event) => {
        event.preventDefault();
        goToStep(2);
    });

    step2Form.addEventListener('submit', (event) => {
        event.preventDefault();
        goToStep(3);
    });

    step3Form.addEventListener('submit', (event) => {
    });

    updateMonthlyWhenAmountOrDurationChanges();
});
