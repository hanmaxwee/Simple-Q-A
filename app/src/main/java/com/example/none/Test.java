package com.example.none;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Test {
        public int id;
        public String name;
        public String synopsis;
        public ArrayList<Question> questions = new ArrayList<Question>();
        public ArrayList<Result> results = new ArrayList<Result>();
        public Test(){}
        public Test(InputStream input) {
                try {
                        parseXml(input);
                }catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                }
        }

        private void parseXml(InputStream input) throws  Exception{
                Question question = null;
                Result result = null;
                //创建一个DocumentBuilderFactory的对象
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(input);
                Node test = document.getFirstChild();
                NodeList children = test.getChildNodes();
                for(int i=0; i<children.getLength(); i++) {
                        Node node = children.item(i);
                        switch(node.getNodeName()) {
                                case "id": this.id = Integer.parseInt( node.getTextContent() ); break;
                                case "name": this.name = node.getTextContent(); break;
                                case "synopsis": this.synopsis = node.getTextContent(); break;
                                case "question": this.questions.add(parseQuestion(node)); break;
                                case "result": this.results.add(parseResult(node)); break;
                        }
                }
                return ;
        }

        private Question parseQuestion(Node qnode){
                Question question = new Question();
                NodeList children = qnode.getChildNodes();
                for(int i=0; i<children.getLength(); i++) {
                        Node node = children.item(i);
                        switch(node.getNodeName()) {
                                case "id": question.id = Integer.parseInt(node.getTextContent()); break;
                                case "title": question.title = node.getTextContent(); break;
                                case "option": question.options.add(parseOption(node)); break;
                        }
                }
                return question;
        }

        private Option parseOption(Node onode){
                Option option = new Option();
                NodeList children = onode.getChildNodes();
                for(int i=0; i<children.getLength(); i++) {
                        Node node = children.item(i);
                        switch(node.getNodeName()) {
                                case "id": option.id = Integer.parseInt(node.getTextContent()); break;
                                case "text": option.text = node.getTextContent(); break;
                                case "score": option.score = Integer.parseInt(node.getTextContent()); break;
                        }
                }
                return option;
        }

        private Result parseResult(Node onode){
                Result result = new Result();
                NodeList children = onode.getChildNodes();
                for(int i=0; i<children.getLength(); i++) {
                        Node node = children.item(i);
                        switch(node.getNodeName()) {
                                case "id": result.id = Integer.parseInt(node.getTextContent()); break;
                                case "text": result.text = node.getTextContent(); break;
                                case "downLimit": result.downLimit = Integer.parseInt(node.getTextContent()); break;
                                case "upperLimit": result.upperLimit = Integer.parseInt(node.getTextContent()); break;
                        }
                }
                return result;
        }

        public int countScore(){
                int totalScore = 0;
                for(Question q : questions){
                        totalScore += q.countScore();
                }
                return totalScore;
        }

        public Result selectResult(int score){
                Result result = null;
                for(Result res : this.results){
                        if(res.downLimit<=score && res.upperLimit>=score){
                                result = res;
                                break;
                        }
                }
                return result;
        }

        public void writeXml(){

        }

        public void recordResult(){

        }

}
