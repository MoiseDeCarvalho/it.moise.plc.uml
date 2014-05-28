//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.03 at 01:06:50 PM CET 
//


package org.plcopen.xml.tc6_0201;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.plcopen.xml.tc6_0201 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.plcopen.xml.tc6_0201
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Project }
     * 
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link VarListPlain }
     * 
     */
    public VarListPlain createVarListPlain() {
        return new VarListPlain();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_ }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_ createValue() {
        return new org.plcopen.xml.tc6_0201.Value_();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_.StructValue }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_.StructValue createValueStructValue() {
        return new org.plcopen.xml.tc6_0201.Value_.StructValue();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_.ArrayValue }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_.ArrayValue createValueArrayValue() {
        return new org.plcopen.xml.tc6_0201.Value_.ArrayValue();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType createDataType() {
        return new org.plcopen.xml.tc6_0201.DataType();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Enum }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Enum createDataTypeEnum() {
        return new org.plcopen.xml.tc6_0201.DataType.Enum();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Enum.Values }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Enum.Values createDataTypeEnumValues() {
        return new org.plcopen.xml.tc6_0201.DataType.Enum.Values();
    }

    /**
     * Create an instance of {@link VarListConfig }
     * 
     */
    public VarListConfig createVarListConfig() {
        return new VarListConfig();
    }

    /**
     * Create an instance of {@link AddDataInfo }
     * 
     */
    public AddDataInfo createAddDataInfo() {
        return new AddDataInfo();
    }

    /**
     * Create an instance of {@link VarListAccess }
     * 
     */
    public VarListAccess createVarListAccess() {
        return new VarListAccess();
    }

    /**
     * Create an instance of {@link Body }
     * 
     */
    public Body createBody() {
        return new Body();
    }

    /**
     * Create an instance of {@link Body.SFC }
     * 
     */
    public Body.SFC createBodySFC() {
        return new Body.SFC();
    }

    /**
     * Create an instance of {@link Body.SFC.SimultaneousDivergence }
     * 
     */
    public Body.SFC.SimultaneousDivergence createBodySFCSimultaneousDivergence() {
        return new Body.SFC.SimultaneousDivergence();
    }

    /**
     * Create an instance of {@link Body.SFC.SelectionConvergence }
     * 
     */
    public Body.SFC.SelectionConvergence createBodySFCSelectionConvergence() {
        return new Body.SFC.SelectionConvergence();
    }

    /**
     * Create an instance of {@link Body.SFC.SelectionDivergence }
     * 
     */
    public Body.SFC.SelectionDivergence createBodySFCSelectionDivergence() {
        return new Body.SFC.SelectionDivergence();
    }

    /**
     * Create an instance of {@link Body.SFC.Transition }
     * 
     */
    public Body.SFC.Transition createBodySFCTransition() {
        return new Body.SFC.Transition();
    }

    /**
     * Create an instance of {@link Body.SFC.Transition.Condition }
     * 
     */
    public Body.SFC.Transition.Condition createBodySFCTransitionCondition() {
        return new Body.SFC.Transition.Condition();
    }

    /**
     * Create an instance of {@link Body.SFC.Step }
     * 
     */
    public Body.SFC.Step createBodySFCStep() {
        return new Body.SFC.Step();
    }

    /**
     * Create an instance of {@link Body.SFC.LeftPowerRail }
     * 
     */
    public Body.SFC.LeftPowerRail createBodySFCLeftPowerRail() {
        return new Body.SFC.LeftPowerRail();
    }

    /**
     * Create an instance of {@link Body.SFC.Block }
     * 
     */
    public Body.SFC.Block createBodySFCBlock() {
        return new Body.SFC.Block();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.OutputVariables }
     * 
     */
    public Body.SFC.Block.OutputVariables createBodySFCBlockOutputVariables() {
        return new Body.SFC.Block.OutputVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.InOutVariables }
     * 
     */
    public Body.SFC.Block.InOutVariables createBodySFCBlockInOutVariables() {
        return new Body.SFC.Block.InOutVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.InputVariables }
     * 
     */
    public Body.SFC.Block.InputVariables createBodySFCBlockInputVariables() {
        return new Body.SFC.Block.InputVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement }
     * 
     */
    public Body.SFC.VendorElement createBodySFCVendorElement() {
        return new Body.SFC.VendorElement();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.OutputVariables }
     * 
     */
    public Body.SFC.VendorElement.OutputVariables createBodySFCVendorElementOutputVariables() {
        return new Body.SFC.VendorElement.OutputVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.InOutVariables }
     * 
     */
    public Body.SFC.VendorElement.InOutVariables createBodySFCVendorElementInOutVariables() {
        return new Body.SFC.VendorElement.InOutVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.InputVariables }
     * 
     */
    public Body.SFC.VendorElement.InputVariables createBodySFCVendorElementInputVariables() {
        return new Body.SFC.VendorElement.InputVariables();
    }

    /**
     * Create an instance of {@link Body.SFC.ActionBlock }
     * 
     */
    public Body.SFC.ActionBlock createBodySFCActionBlock() {
        return new Body.SFC.ActionBlock();
    }

    /**
     * Create an instance of {@link Body.SFC.ActionBlock.Action }
     * 
     */
    public Body.SFC.ActionBlock.Action createBodySFCActionBlockAction() {
        return new Body.SFC.ActionBlock.Action();
    }

    /**
     * Create an instance of {@link AddData }
     * 
     */
    public AddData createAddData() {
        return new AddData();
    }

    /**
     * Create an instance of {@link Project.Instances }
     * 
     */
    public Project.Instances createProjectInstances() {
        return new Project.Instances();
    }

    /**
     * Create an instance of {@link Project.Instances.Configurations }
     * 
     */
    public Project.Instances.Configurations createProjectInstancesConfigurations() {
        return new Project.Instances.Configurations();
    }

    /**
     * Create an instance of {@link Project.Instances.Configurations.Configuration }
     * 
     */
    public Project.Instances.Configurations.Configuration createProjectInstancesConfigurationsConfiguration() {
        return new Project.Instances.Configurations.Configuration();
    }

    /**
     * Create an instance of {@link Project.Instances.Configurations.Configuration.Resource }
     * 
     */
    public Project.Instances.Configurations.Configuration.Resource createProjectInstancesConfigurationsConfigurationResource() {
        return new Project.Instances.Configurations.Configuration.Resource();
    }

    /**
     * Create an instance of {@link Project.Types }
     * 
     */
    public Project.Types createProjectTypes() {
        return new Project.Types();
    }

    /**
     * Create an instance of {@link Project.Types.Pous }
     * 
     */
    public Project.Types.Pous createProjectTypesPous() {
        return new Project.Types.Pous();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou }
     * 
     */
    public Project.Types.Pous.Pou createProjectTypesPousPou() {
        return new Project.Types.Pous.Pou();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Transitions }
     * 
     */
    public Project.Types.Pous.Pou.Transitions createProjectTypesPousPouTransitions() {
        return new Project.Types.Pous.Pou.Transitions();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Actions }
     * 
     */
    public Project.Types.Pous.Pou.Actions createProjectTypesPousPouActions() {
        return new Project.Types.Pous.Pou.Actions();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface }
     * 
     */
    public Project.Types.Pous.Pou.Interface createProjectTypesPousPouInterface() {
        return new Project.Types.Pous.Pou.Interface();
    }

    /**
     * Create an instance of {@link Project.Types.DataTypes }
     * 
     */
    public Project.Types.DataTypes createProjectTypesDataTypes() {
        return new Project.Types.DataTypes();
    }

    /**
     * Create an instance of {@link Project.ContentHeader }
     * 
     */
    public Project.ContentHeader createProjectContentHeader() {
        return new Project.ContentHeader();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo }
     * 
     */
    public Project.ContentHeader.CoordinateInfo createProjectContentHeaderCoordinateInfo() {
        return new Project.ContentHeader.CoordinateInfo();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Sfc }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Sfc createProjectContentHeaderCoordinateInfoSfc() {
        return new Project.ContentHeader.CoordinateInfo.Sfc();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Ld }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Ld createProjectContentHeaderCoordinateInfoLd() {
        return new Project.ContentHeader.CoordinateInfo.Ld();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Fbd }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Fbd createProjectContentHeaderCoordinateInfoFbd() {
        return new Project.ContentHeader.CoordinateInfo.Fbd();
    }

    /**
     * Create an instance of {@link Project.FileHeader }
     * 
     */
    public Project.FileHeader createProjectFileHeader() {
        return new Project.FileHeader();
    }

    /**
     * Create an instance of {@link FormattedText }
     * 
     */
    public FormattedText createFormattedText() {
        return new FormattedText();
    }

    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link Connection }
     * 
     */
    public Connection createConnection() {
        return new Connection();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.ConnectionPointIn }
     * 
     */
    public org.plcopen.xml.tc6_0201.ConnectionPointIn createConnectionPointIn() {
        return new org.plcopen.xml.tc6_0201.ConnectionPointIn();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.ConnectionPointOut }
     * 
     */
    public org.plcopen.xml.tc6_0201.ConnectionPointOut createConnectionPointOut() {
        return new org.plcopen.xml.tc6_0201.ConnectionPointOut();
    }

    /**
     * Create an instance of {@link RangeSigned }
     * 
     */
    public RangeSigned createRangeSigned() {
        return new RangeSigned();
    }

    /**
     * Create an instance of {@link PouInstance }
     * 
     */
    public PouInstance createPouInstance() {
        return new PouInstance();
    }

    /**
     * Create an instance of {@link RangeUnsigned }
     * 
     */
    public RangeUnsigned createRangeUnsigned() {
        return new RangeUnsigned();
    }

    /**
     * Create an instance of {@link VarList }
     * 
     */
    public VarList createVarList() {
        return new VarList();
    }

    /**
     * Create an instance of {@link VarListPlain.Variable }
     * 
     */
    public VarListPlain.Variable createVarListPlainVariable() {
        return new VarListPlain.Variable();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_.SimpleValue }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_.SimpleValue createValueSimpleValue() {
        return new org.plcopen.xml.tc6_0201.Value_.SimpleValue();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_.StructValue.Value }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_.StructValue.Value createValueStructValueValue() {
        return new org.plcopen.xml.tc6_0201.Value_.StructValue.Value();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.Value_.ArrayValue.Value }
     * 
     */
    public org.plcopen.xml.tc6_0201.Value_.ArrayValue.Value createValueArrayValueValue() {
        return new org.plcopen.xml.tc6_0201.Value_.ArrayValue.Value();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.String }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.String createDataTypeString() {
        return new org.plcopen.xml.tc6_0201.DataType.String();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Wstring }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Wstring createDataTypeWstring() {
        return new org.plcopen.xml.tc6_0201.DataType.Wstring();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Array }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Array createDataTypeArray() {
        return new org.plcopen.xml.tc6_0201.DataType.Array();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Derived }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Derived createDataTypeDerived() {
        return new org.plcopen.xml.tc6_0201.DataType.Derived();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.SubrangeSigned }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.SubrangeSigned createDataTypeSubrangeSigned() {
        return new org.plcopen.xml.tc6_0201.DataType.SubrangeSigned();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.SubrangeUnsigned }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.SubrangeUnsigned createDataTypeSubrangeUnsigned() {
        return new org.plcopen.xml.tc6_0201.DataType.SubrangeUnsigned();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Pointer }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Pointer createDataTypePointer() {
        return new org.plcopen.xml.tc6_0201.DataType.Pointer();
    }

    /**
     * Create an instance of {@link org.plcopen.xml.tc6_0201.DataType.Enum.Values.Value }
     * 
     */
    public org.plcopen.xml.tc6_0201.DataType.Enum.Values.Value createDataTypeEnumValuesValue() {
        return new org.plcopen.xml.tc6_0201.DataType.Enum.Values.Value();
    }

    /**
     * Create an instance of {@link VarListConfig.ConfigVariable }
     * 
     */
    public VarListConfig.ConfigVariable createVarListConfigConfigVariable() {
        return new VarListConfig.ConfigVariable();
    }

    /**
     * Create an instance of {@link AddDataInfo.Info }
     * 
     */
    public AddDataInfo.Info createAddDataInfoInfo() {
        return new AddDataInfo.Info();
    }

    /**
     * Create an instance of {@link VarListAccess.AccessVariable }
     * 
     */
    public VarListAccess.AccessVariable createVarListAccessAccessVariable() {
        return new VarListAccess.AccessVariable();
    }

    /**
     * Create an instance of {@link Body.FBD }
     * 
     */
    public Body.FBD createBodyFBD() {
        return new Body.FBD();
    }

    /**
     * Create an instance of {@link Body.LD }
     * 
     */
    public Body.LD createBodyLD() {
        return new Body.LD();
    }

    /**
     * Create an instance of {@link Body.SFC.Comment }
     * 
     */
    public Body.SFC.Comment createBodySFCComment() {
        return new Body.SFC.Comment();
    }

    /**
     * Create an instance of {@link Body.SFC.Error }
     * 
     */
    public Body.SFC.Error createBodySFCError() {
        return new Body.SFC.Error();
    }

    /**
     * Create an instance of {@link Body.SFC.Connector }
     * 
     */
    public Body.SFC.Connector createBodySFCConnector() {
        return new Body.SFC.Connector();
    }

    /**
     * Create an instance of {@link Body.SFC.Continuation }
     * 
     */
    public Body.SFC.Continuation createBodySFCContinuation() {
        return new Body.SFC.Continuation();
    }

    /**
     * Create an instance of {@link Body.SFC.InVariable }
     * 
     */
    public Body.SFC.InVariable createBodySFCInVariable() {
        return new Body.SFC.InVariable();
    }

    /**
     * Create an instance of {@link Body.SFC.OutVariable }
     * 
     */
    public Body.SFC.OutVariable createBodySFCOutVariable() {
        return new Body.SFC.OutVariable();
    }

    /**
     * Create an instance of {@link Body.SFC.InOutVariable }
     * 
     */
    public Body.SFC.InOutVariable createBodySFCInOutVariable() {
        return new Body.SFC.InOutVariable();
    }

    /**
     * Create an instance of {@link Body.SFC.Label }
     * 
     */
    public Body.SFC.Label createBodySFCLabel() {
        return new Body.SFC.Label();
    }

    /**
     * Create an instance of {@link Body.SFC.Jump }
     * 
     */
    public Body.SFC.Jump createBodySFCJump() {
        return new Body.SFC.Jump();
    }

    /**
     * Create an instance of {@link Body.SFC.Return }
     * 
     */
    public Body.SFC.Return createBodySFCReturn() {
        return new Body.SFC.Return();
    }

    /**
     * Create an instance of {@link Body.SFC.RightPowerRail }
     * 
     */
    public Body.SFC.RightPowerRail createBodySFCRightPowerRail() {
        return new Body.SFC.RightPowerRail();
    }

    /**
     * Create an instance of {@link Body.SFC.Coil }
     * 
     */
    public Body.SFC.Coil createBodySFCCoil() {
        return new Body.SFC.Coil();
    }

    /**
     * Create an instance of {@link Body.SFC.Contact }
     * 
     */
    public Body.SFC.Contact createBodySFCContact() {
        return new Body.SFC.Contact();
    }

    /**
     * Create an instance of {@link Body.SFC.MacroStep }
     * 
     */
    public Body.SFC.MacroStep createBodySFCMacroStep() {
        return new Body.SFC.MacroStep();
    }

    /**
     * Create an instance of {@link Body.SFC.JumpStep }
     * 
     */
    public Body.SFC.JumpStep createBodySFCJumpStep() {
        return new Body.SFC.JumpStep();
    }

    /**
     * Create an instance of {@link Body.SFC.SimultaneousConvergence }
     * 
     */
    public Body.SFC.SimultaneousConvergence createBodySFCSimultaneousConvergence() {
        return new Body.SFC.SimultaneousConvergence();
    }

    /**
     * Create an instance of {@link Body.SFC.SimultaneousDivergence.ConnectionPointOut }
     * 
     */
    public Body.SFC.SimultaneousDivergence.ConnectionPointOut createBodySFCSimultaneousDivergenceConnectionPointOut() {
        return new Body.SFC.SimultaneousDivergence.ConnectionPointOut();
    }

    /**
     * Create an instance of {@link Body.SFC.SelectionConvergence.ConnectionPointIn }
     * 
     */
    public Body.SFC.SelectionConvergence.ConnectionPointIn createBodySFCSelectionConvergenceConnectionPointIn() {
        return new Body.SFC.SelectionConvergence.ConnectionPointIn();
    }

    /**
     * Create an instance of {@link Body.SFC.SelectionDivergence.ConnectionPointOut }
     * 
     */
    public Body.SFC.SelectionDivergence.ConnectionPointOut createBodySFCSelectionDivergenceConnectionPointOut() {
        return new Body.SFC.SelectionDivergence.ConnectionPointOut();
    }

    /**
     * Create an instance of {@link Body.SFC.Transition.Condition.Reference }
     * 
     */
    public Body.SFC.Transition.Condition.Reference createBodySFCTransitionConditionReference() {
        return new Body.SFC.Transition.Condition.Reference();
    }

    /**
     * Create an instance of {@link Body.SFC.Transition.Condition.Inline }
     * 
     */
    public Body.SFC.Transition.Condition.Inline createBodySFCTransitionConditionInline() {
        return new Body.SFC.Transition.Condition.Inline();
    }

    /**
     * Create an instance of {@link Body.SFC.Step.ConnectionPointOut }
     * 
     */
    public Body.SFC.Step.ConnectionPointOut createBodySFCStepConnectionPointOut() {
        return new Body.SFC.Step.ConnectionPointOut();
    }

    /**
     * Create an instance of {@link Body.SFC.Step.ConnectionPointOutAction }
     * 
     */
    public Body.SFC.Step.ConnectionPointOutAction createBodySFCStepConnectionPointOutAction() {
        return new Body.SFC.Step.ConnectionPointOutAction();
    }

    /**
     * Create an instance of {@link Body.SFC.LeftPowerRail.ConnectionPointOut }
     * 
     */
    public Body.SFC.LeftPowerRail.ConnectionPointOut createBodySFCLeftPowerRailConnectionPointOut() {
        return new Body.SFC.LeftPowerRail.ConnectionPointOut();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.OutputVariables.Variable }
     * 
     */
    public Body.SFC.Block.OutputVariables.Variable createBodySFCBlockOutputVariablesVariable() {
        return new Body.SFC.Block.OutputVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.InOutVariables.Variable }
     * 
     */
    public Body.SFC.Block.InOutVariables.Variable createBodySFCBlockInOutVariablesVariable() {
        return new Body.SFC.Block.InOutVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.Block.InputVariables.Variable }
     * 
     */
    public Body.SFC.Block.InputVariables.Variable createBodySFCBlockInputVariablesVariable() {
        return new Body.SFC.Block.InputVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.OutputVariables.Variable }
     * 
     */
    public Body.SFC.VendorElement.OutputVariables.Variable createBodySFCVendorElementOutputVariablesVariable() {
        return new Body.SFC.VendorElement.OutputVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.InOutVariables.Variable }
     * 
     */
    public Body.SFC.VendorElement.InOutVariables.Variable createBodySFCVendorElementInOutVariablesVariable() {
        return new Body.SFC.VendorElement.InOutVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.VendorElement.InputVariables.Variable }
     * 
     */
    public Body.SFC.VendorElement.InputVariables.Variable createBodySFCVendorElementInputVariablesVariable() {
        return new Body.SFC.VendorElement.InputVariables.Variable();
    }

    /**
     * Create an instance of {@link Body.SFC.ActionBlock.Action.Reference }
     * 
     */
    public Body.SFC.ActionBlock.Action.Reference createBodySFCActionBlockActionReference() {
        return new Body.SFC.ActionBlock.Action.Reference();
    }

    /**
     * Create an instance of {@link AddData.Data }
     * 
     */
    public AddData.Data createAddDataData() {
        return new AddData.Data();
    }

    /**
     * Create an instance of {@link Project.Instances.Configurations.Configuration.Resource.Task }
     * 
     */
    public Project.Instances.Configurations.Configuration.Resource.Task createProjectInstancesConfigurationsConfigurationResourceTask() {
        return new Project.Instances.Configurations.Configuration.Resource.Task();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Transitions.Transition }
     * 
     */
    public Project.Types.Pous.Pou.Transitions.Transition createProjectTypesPousPouTransitionsTransition() {
        return new Project.Types.Pous.Pou.Transitions.Transition();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Actions.Action }
     * 
     */
    public Project.Types.Pous.Pou.Actions.Action createProjectTypesPousPouActionsAction() {
        return new Project.Types.Pous.Pou.Actions.Action();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.LocalVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.LocalVars createProjectTypesPousPouInterfaceLocalVars() {
        return new Project.Types.Pous.Pou.Interface.LocalVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.TempVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.TempVars createProjectTypesPousPouInterfaceTempVars() {
        return new Project.Types.Pous.Pou.Interface.TempVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.InputVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.InputVars createProjectTypesPousPouInterfaceInputVars() {
        return new Project.Types.Pous.Pou.Interface.InputVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.OutputVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.OutputVars createProjectTypesPousPouInterfaceOutputVars() {
        return new Project.Types.Pous.Pou.Interface.OutputVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.InOutVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.InOutVars createProjectTypesPousPouInterfaceInOutVars() {
        return new Project.Types.Pous.Pou.Interface.InOutVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.ExternalVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.ExternalVars createProjectTypesPousPouInterfaceExternalVars() {
        return new Project.Types.Pous.Pou.Interface.ExternalVars();
    }

    /**
     * Create an instance of {@link Project.Types.Pous.Pou.Interface.GlobalVars }
     * 
     */
    public Project.Types.Pous.Pou.Interface.GlobalVars createProjectTypesPousPouInterfaceGlobalVars() {
        return new Project.Types.Pous.Pou.Interface.GlobalVars();
    }

    /**
     * Create an instance of {@link Project.Types.DataTypes.DataType }
     * 
     */
    public Project.Types.DataTypes.DataType createProjectTypesDataTypesDataType() {
        return new Project.Types.DataTypes.DataType();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.PageSize }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.PageSize createProjectContentHeaderCoordinateInfoPageSize() {
        return new Project.ContentHeader.CoordinateInfo.PageSize();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Sfc.Scaling }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Sfc.Scaling createProjectContentHeaderCoordinateInfoSfcScaling() {
        return new Project.ContentHeader.CoordinateInfo.Sfc.Scaling();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Ld.Scaling }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Ld.Scaling createProjectContentHeaderCoordinateInfoLdScaling() {
        return new Project.ContentHeader.CoordinateInfo.Ld.Scaling();
    }

    /**
     * Create an instance of {@link Project.ContentHeader.CoordinateInfo.Fbd.Scaling }
     * 
     */
    public Project.ContentHeader.CoordinateInfo.Fbd.Scaling createProjectContentHeaderCoordinateInfoFbdScaling() {
        return new Project.ContentHeader.CoordinateInfo.Fbd.Scaling();
    }
    
    

}