import { AxiosResponse, CancelTokenSource } from 'axios';
import api from './api';

class PersonService {

    private apiVersion: string = "v1";

    save(data: any, cancelTokenSource?: CancelTokenSource): Promise<AxiosResponse<any, any>> {
        return api.post<any>(`${this.apiVersion}/person`, data, { cancelToken: cancelTokenSource?.token });
    }

}

export default new PersonService();