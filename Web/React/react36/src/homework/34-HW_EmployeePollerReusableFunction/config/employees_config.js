export const MIN_SALARY = 5000;
export const MAX_SALARY = 35000;
export const GENDERS = ['male', 'female'];
export const NAMES = {
    'male' : ['Asher', 'Yuri', 'Grigory', 'Eduard',
        'Igor', 'Alex', 'Moses', 'Yakob', 'David', 'Sergey',
        'Ivan'],
    'female' : ['Katya', 'Olga', 'Sara', 'Masha',
        'Irina', 'Polina', 'Rivka', 'Proskovya', 'Nina', 'Margo']
}
export const NAME_MIN_LENGTH = 4;
export const DIGITS_ID = 5;
export const TITLES = ['Developer', 'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager'];
export const PATH_EMPLOYEES = '/employees';
export const PATH_TITLE_STATISTICS = '/title/statistics' ;
export const PATH_SEARCH = '/search';
export const PATH_GENERATION = '/generation';
export const PATH_SALARY_STATISTICS = '/salary/statistics';
export const MAX_EMPLOYEES_ONE_GENERATION = 100;
export const LINKS = [
    {path: PATH_EMPLOYEES, label: 'Employees'},
    {path: PATH_GENERATION, label: 'Employees Generation'},
    {path: PATH_SALARY_STATISTICS, label: 'Salary Statistics'},
    {path: PATH_TITLE_STATISTICS, label: 'Title Statistics'},
    {path: PATH_SEARCH, label: 'Searching'}
]
export const POLLING_INTERVAL = 1000

