package com.zangmz.hit.medicineneo4j.utils;


import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.lang.String;

public class owlExtract {

    public static void main(String[] args) throws FileNotFoundException {
        // 创建本体模型
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read("C:/Users/25350/IdeaProjects/owlTestExtract/file/pmo_v3.0.owl"); // 读取文件，加载模型

        //annotation properties
        String PMO = "http://www.PMO.com/";
        AnnotationProperty Definition = ontModel.createAnnotationProperty("http://www.PMO.com/Definition");
        AnnotationProperty Example = ontModel.createAnnotationProperty("http://www.PMO.com/Example");
        AnnotationProperty MCID = ontModel.createAnnotationProperty("http://www.PMO.com/MCID");
        AnnotationProperty MRID = ontModel.createAnnotationProperty("http://www.PMO.com/MRID");
        AnnotationProperty PMOID = ontModel.createAnnotationProperty("http://www.PMO.com/PMOID");
        AnnotationProperty Source_of_Example = ontModel.createAnnotationProperty("http://www.PMO.com/Source_of_Example");
        AnnotationProperty Subclass_of = ontModel.createAnnotationProperty("http://www.PMO.com/Subclass_of");
        AnnotationProperty Synonym = ontModel.createAnnotationProperty("http://www.PMO.com/Synonym");
        AnnotationProperty Tree_Number = ontModel.createAnnotationProperty("http://www.PMO.com/Tree_Number");
        AnnotationProperty database_cross_reference = ontModel.createAnnotationProperty("http://www.PMO.com/database_cross_reference");


        for (Iterator<?> i = ontModel.listClasses(); i.hasNext(); ) {
            OntClass c = (OntClass) i.next();
            if (!c.isAnon()) {
                System.out.println("Class:\nlocal name: " + c.getLocalName());
                System.out.println("label name: " + c.getLabel(""));
                //the super
                for (Iterator<?> it = c.listSubClasses(); it.hasNext(); ) {
                    OntClass sp = (OntClass) it.next();
                    if (!sp.isAnon()) {
                        String str = c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()) + "'s superClass is "; // 获取URI
                        String strSP = sp.getURI();
                        if (strSP != null) {
                            try { // 另一种简化处理URI的方法
                                str = str + ":" + strSP.substring(strSP.indexOf('#') + 1);
                                System.out.println("Class" + str);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
            System.out.println("Definition:" + c.getPropertyValue(Definition));
            System.out.println("Example:" + c.getPropertyValue(Example));
            System.out.println("MCID:" + c.getPropertyValue(MCID));
            System.out.println("MRID:" + c.getPropertyValue(MRID));
            System.out.println("PMOID:" + c.getPropertyValue(PMOID));
            System.out.println("Source_of_Example:" + c.getPropertyValue(Source_of_Example));
            System.out.println("Subclass_of:" + c.getPropertyValue(Subclass_of));
            System.out.println("Synonym:" + c.getPropertyValue(Synonym));
            System.out.println("Tree_Number:" + c.getPropertyValue(Tree_Number));
            System.out.println("database_cross_reference:" + c.getPropertyValue(database_cross_reference));
            System.out.println("\n");
        }
    }
}


