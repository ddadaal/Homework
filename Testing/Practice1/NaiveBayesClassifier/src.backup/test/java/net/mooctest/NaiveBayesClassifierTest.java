/*
 * This file was automatically generated by EvoSuite
 * Wed Apr 17 08:13:08 CST 2019
 */

package net.mooctest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.LocalClassDeclarationStmt;
import com.github.javaparser.ast.type.PrimitiveType;


public class NaiveBayesClassifierTest {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      NaiveBayesClassifier<Integer, Object> naiveBayesClassifier0 = new NaiveBayesClassifier<Integer, Object>();
      LinkedHashSet<Integer> linkedHashSet0 = new LinkedHashSet<Integer>();
      Object object0 = new Object();
      naiveBayesClassifier0.learn(object0, (Collection<Integer>) linkedHashSet0);
      Vector<Integer> vector0 = new Vector<Integer>();
      Collection<Classification<Integer, Object>> collection0 = naiveBayesClassifier0.classifyDetailed(vector0);
      assertNotNull(collection0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      NaiveBayesClassifier<PrimitiveType.Primitive, String> naiveBayesClassifier0 = new NaiveBayesClassifier<PrimitiveType.Primitive, String>();
      Vector<PrimitiveType.Primitive> vector0 = new Vector<PrimitiveType.Primitive>();
      naiveBayesClassifier0.learn("", (Collection<PrimitiveType.Primitive>) vector0);
      // Undeclared exception!
      try { 
        naiveBayesClassifier0.classify((Collection<PrimitiveType.Primitive>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      NaiveBayesClassifier<MethodDeclaration, Object> naiveBayesClassifier0 = new NaiveBayesClassifier<MethodDeclaration, Object>();
      TreeSet<MethodDeclaration> treeSet0 = new TreeSet<MethodDeclaration>();
      Classification<MethodDeclaration, Object> classification0 = naiveBayesClassifier0.classify(treeSet0);
      assertNull(classification0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      NaiveBayesClassifier<Integer, Object> naiveBayesClassifier0 = new NaiveBayesClassifier<Integer, Object>();
      LinkedHashSet<Integer> linkedHashSet0 = new LinkedHashSet<Integer>();
      Object object0 = new Object();
      Classification<Integer, Object> classification0 = new Classification<Integer, Object>(linkedHashSet0, object0, 0.0F);
      naiveBayesClassifier0.learn(classification0);
      Classification<Integer, Object> classification1 = new Classification<Integer, Object>(linkedHashSet0, linkedHashSet0, 0.0F);
      naiveBayesClassifier0.learn(classification1);
      Collection<Classification<Integer, Object>> collection0 = naiveBayesClassifier0.classifyDetailed(linkedHashSet0);
      assertFalse(collection0.contains(classification0));
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      NaiveBayesClassifier<Integer, Object> naiveBayesClassifier0 = new NaiveBayesClassifier<Integer, Object>();
      LinkedHashSet<Integer> linkedHashSet0 = new LinkedHashSet<Integer>();
      Object object0 = new Object();
      naiveBayesClassifier0.learn(object0, (Collection<Integer>) linkedHashSet0);
      Classification<Integer, Object> classification0 = new Classification<Integer, Object>(linkedHashSet0, object0, 0.0F);
      naiveBayesClassifier0.learn(classification0);
      Classification<Integer, Object> classification1 = new Classification<Integer, Object>(linkedHashSet0, linkedHashSet0, 0.0F);
      naiveBayesClassifier0.learn(classification1);
      Vector<Integer> vector0 = new Vector<Integer>();
      Collection<Classification<Integer, Object>> collection0 = naiveBayesClassifier0.classifyDetailed(vector0);
      assertFalse(collection0.contains(classification0));
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      NaiveBayesClassifier<LocalClassDeclarationStmt, Integer> naiveBayesClassifier0 = new NaiveBayesClassifier<LocalClassDeclarationStmt, Integer>();
      Integer integer0 = new Integer(0);
      LinkedHashSet<LocalClassDeclarationStmt> linkedHashSet0 = new LinkedHashSet<LocalClassDeclarationStmt>();
      LocalClassDeclarationStmt localClassDeclarationStmt0 = new LocalClassDeclarationStmt();
      linkedHashSet0.add(localClassDeclarationStmt0);
      naiveBayesClassifier0.learn(integer0, (Collection<LocalClassDeclarationStmt>) linkedHashSet0);
      Classification<LocalClassDeclarationStmt, Integer> classification0 = naiveBayesClassifier0.classify(linkedHashSet0);
      assertNotNull(classification0);
      assertEquals(0.75F, classification0.getProbability(), 0.01F);
  }

}
