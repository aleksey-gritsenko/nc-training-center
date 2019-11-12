import {Id} from '../models/id'

export class Book extends Id{
  header: string;
  author:string;
  overview: string; // maybe Text?
  photo:number;
  file:number;
  status: string;
}

