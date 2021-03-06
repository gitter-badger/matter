package de.hyronx.matter.compiler.ast

import de.hyronx.matter.compiler.types._

sealed trait MappingAST extends AST
case class Mappings(mappings: Seq[Mapping]) extends AST
case class IfExpression(boolOp: MappingAST) extends MappingAST
case class ElseExpression(op: MappingAST) extends MappingAST
case class CallExpression(
    caller: VariableLike,
    callee: VariableLike
) extends MappingAST with VariableLike {
  val name = caller.name
  val varType = callee.varType
}

case class IfStatement(boolOp: MappingAST, body: Seq[MappingAST]) extends MappingAST
case class ElseStatement(body: Seq[MappingAST]) extends MappingAST

case object StringMapping extends MappingAST
case object BoolMapping extends MappingAST
case object IntMapping extends MappingAST
case object FloatMapping extends MappingAST

sealed trait Mapping extends AST {
  def syntaxVar: String
  def mappedVar: String
  def varType: Type
}
case class MappingExpression(syntaxVar: String, mappedVar: String, varType: Type, op: MappingAST) extends Mapping
case class MappingStatement(syntaxVar: String, mappedVar: String, varType: Type, op: Seq[MappingAST]) extends Mapping
