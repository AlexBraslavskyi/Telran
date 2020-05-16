import {Axios} from "axios-observable";
import {map} from "rxjs/operators"
export default class AuthJwtService {
    constructor(url) {
        if (!url) {
            throw Error('url is undefined');
        }
        this.url = url;
    }
    register(users) {
        Axios.get(this.url + 'users')
            .pipe(map(response => response.data))
            .subscribe(data => {
                if (!data || data.length === 0) {
                    users.forEach(user => {
                        Axios.post(this.url + 'register', user)
                            .subscribe();
                    })

                }
            })
    }
    login(credentials) {
        return Axios.post(this.url + 'login', credentials)
            .pipe(map(response => response.data.accessToken))
    }
    logout() {
        localStorage.removeItem('accessToken');
    }
    getUsername() {
        const jwt = localStorage.getItem('accessToken');
        if (!jwt) {
            return '';
        }
        const jwtBody = JSON.parse(atob(jwt.split('.')[1]));
        console.log(jwtBody);
        const currentTimeInSeconds = new Date() / 1000;
        if (currentTimeInSeconds > jwtBody.exp) {
            this.logout();
            return '';
        }
        return jwtBody.email;
    }
}
